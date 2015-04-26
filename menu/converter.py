#!/usr/bin/env python
# -*- coding: UTF-8 -*-
# coding=utf-8

import sys
from openpyxl import load_workbook
from datetime import datetime, timedelta

def Help(msg=""):
	print "Converts ahnlab Menu to Outlook compativle csv"
	if msg !="":
		print "Error: %s" % (msg)
	sys.exit(-1)


def range_finder(ws, Column, search_str, padding=0, heading=0, start=1, end=100):
	cell_start=None
	cell_end=None

	for i in range(start,end):
		cell=Column+str(i)

		if cell_start!=None:
			if ws[cell].value!=None :
				cell_end=i-1+padding;
				break;

		if cell_start==None:
			if ws[cell].value!=None:
				if search_str=="":
					cell_start=i+heading;
				elif ws[cell].value.find(search_str) > 0:
					cell_start=i+heading;

	retval={'column':Column, \
			'data':ws[Column+str(cell_start)].value, \
			'row':{ 'start':cell_start, 'end':cell_end}, \
			'cell':{ 'start':Column+str(cell_start), 'end':Column+str(cell_end)} \
			}

	return retval

def data_finder(ws, Column, start, end):
	retval=[]

	# print "data_finder: start=",start," end=",end
	# find start point first, then add end points.
	temps=[]
	for i in range(start, end*2):
		cell=Column+str(i)
		if ws[cell].value!=None:
			temps.append(i)

	#print "temps: ",temps
	j=1
	for i in range(start, end+1):
		cell=Column+str(i)
		if ws[cell].value!=None:
			
			if temps[j]-1 >= end:
				real_end=end
			else:
				real_end=temps[j]-1
			#real_end=end

			subval={'column':Column, \
					'data':ws[cell].value, \
					'row':{'start':i, 'end':real_end}, \
					'cell':{'start':cell, 'end':Column+str(real_end)} \
					}
			retval.append(subval)
			j+=1

	return retval


def data_merger(ws, Column, start, end, header=""):
	# print "rx=", start, end
	retval=""
	for i in range(start, end+1):
		cell=Column+str(i)
		# print "i=",cell
		if ws[cell].value==None:
			retval+="\n"
		else:
			retval+="\n"+ws[cell].value
	
	if len(retval)>1:
		return "\n["+header.replace("\n"," ")+"]"+retval
	else:
		return ""

def main(filename):
	print "loading: "+filename
	wb=load_workbook(filename)
	# activate worksheet
	ws=wb.active

	# ----- 플러스메뉴 fix 시작
	# BreakfastMenu Fix
	# 플러스 메뉴 병합 해제
	ws.unmerge_cells('A14:B14')
	# '플러스메뉴' (A14) 를 (B14)로 복사
	ws['B14']=ws['A14'].value
	# A14 클리어
	ws['A14']=""
	# 원래의 아침메뉴 Range를 병합해제
	ws.unmerge_cells('A3:A13')
	# 플러스 메뉴가 있는 부분까지 아침메뉴로 병합
	ws.merge_cells('A3:A14')

	# LunchMenu Fix
	ws.unmerge_cells('A39:B42')
	ws['B39']=ws['A39'].value
	ws.merge_cells('B39:B42')
	ws['A39']=""
	ws.unmerge_cells('A15:A38')
	ws.merge_cells('A15:A42')

	# DinnerMenu Fix
	ws.unmerge_cells('A51:B51')
	ws['B51']=ws['A51'].value
	ws['A51']=""
	ws.unmerge_cells('A43:A50')
	ws.merge_cells('A43:A51')

	# End of Data Fix.
	# 마지막에 글자를 넣어줘야 range를 구할 수 있음
	last_row=52
	ws.unmerge_cells('A'+str(last_row)+':G'+str(last_row))
	ws['B'+str(last_row)]="."
	ws['C'+str(last_row)]="."
	ws['D'+str(last_row)]="."
	ws['E'+str(last_row)]="."
	ws['F'+str(last_row)]="."
	ws['G'+str(last_row)]="."
	# ----- 플러스메뉴 fix 끝 


	# d=datetime.date.today()
	# year=d.year
	# month=ws['D2'].value.split(" ")[0].replace("월","")
	# day=ws['D2'].value.split(" ")[1].replace("일","")
	date_start=datetime.strptime( str(datetime.now().year)+' '+ ws['D2'].value.split(" ")[0].replace(u"\uc6d4","")+' '+ str(int(ws['D2'].value.split(" ")[1].replace(u"\uc77c",""))-1), '%Y %m %d')
	
	filename_date_start=date_start+timedelta(-1)
	filename_date_end=filename_date_start+timedelta(6)

	outputfilename=str(filename_date_start.strftime('%Y%m%d'))+"-"+str(filename_date_end.strftime('%Y%m%d'))+".csv"
	outputfile=open(outputfilename, "w")

	print "outputfilename: ",outputfilename
	csvHeader=u"제목,시작 날짜,시작 시간,끝 날짜,끝 시간,하루 종일,미리 알림 설정/해제,미리 알림 날짜,미리 알림 시간,모임 이끌이,필수 참석자,선택 참석자,모임 리소스,거리,범주 항목,비용 정보,설명,숨김,시간 상태 보기,우선 순위,우편물 종류,장소"
	outputfile.write(csvHeader.encode('utf8')+"\n")


	# date_start.strftime('%Y-%m-%d')


	# To see what happened, uncomment follow
	# wb.save("converted.xlsx")

	# calculate range between breakfast, lunch, dinner
	numday=0
	#for col in ['C']:
	for col in ['C','D','E','F','G']:

		today=date_start+timedelta(numday)
		#for time in ['07:30']:
		for time in ['07:30','11:30','17:30']:

			if time=='07:30':
				ettd=60
				title=u"아침메뉴"
			if time=='11:30':
				ettd=90
				title=u"점심메뉴"
			if time=='17:30':
				ettd=90
				title=u"저녁메뉴"

			start_day=str(today.strftime('%m/%d/%Y'))
			start_time=time+":00"
			end_day=str(today.strftime('%m/%d/%Y'))
			end_time=datetime.strptime(time+":00",'%H:%M:%S')+timedelta(minutes=ettd)
			end_time=str(end_time.strftime('%H:%M:%S'))
			all_day="FALSE"
			notification_enabled="FALSE"
			notification_day=str(today.strftime('%m/%d/%Y'))
			notification_time=time
			meeting_host="Nine2Six"
			other_fields01=',,,,,,'
			other_fields02=u'FALSE,2,중간,보통,'

			#print "Date: ", today.strftime('%Y-%m-%d'), "Time: ", time, end_time.strftime('%H:%M')

			meal=range_finder(ws, "A", time)
			# print meal
			meal_category=data_finder(ws, "B", start=meal['row']['start'], end=meal['row']['end'])
			# print meal_category

			old_prnstr=""
			description=""
			for i in range(0,len(meal_category)):
				# print "meal_category[i]['data']", meal_category[i]['data'].strip("\n")
				# print meal_category[i]['row']['start'], meal_category[i]['row']['end']
				description+=data_merger(ws, col, meal_category[i]['row']['start'], meal_category[i]['row']['end'], header=meal_category[i]['data'])+"\n"
				
			new_prnstr=title+','+start_day+','+start_time+','+end_day+','+end_time+','+all_day+','+ \
				notification_enabled+','+notification_day+','+notification_time+','+meeting_host+','+ \
				other_fields01+'"'+description+'",'+other_fields02
			
			outputfile.write(new_prnstr.encode('utf8')+"\n")

			# skip on same line
			if old_prnstr!=new_prnstr :
				#print title+','+start_day+','+start_time+','+end_day+','+end_time+','+all_day+','+ \
				notification_enabled+','+notification_day+','+notification_time+','+meeting_host+','+ \
				other_fields01+'"'+description+'",'+other_fields02

				old_prnstr=new_prnstr

		numday+=1

	outputfile.close()
if __name__ == '__main__':
	if len(sys.argv[1])==0:
		filename="42751.xlsx"
	else:
		filename=sys.argv[1]
	
	main(filename)