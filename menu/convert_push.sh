#!/bin/bash
datapath=.
/usr/bin/iconv -f euc-kr -t utf-8 $datapath/csv_export.csv > $datapath/csv_export_utf8.csv
#git add $datapath/csv_export_utf8.csv
git commit -m "Automatic Data Change Commit"
#git push



