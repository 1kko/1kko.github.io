App = Ember.Application.create();

App.Router.map(function()
{
	this.resource('menus', function() {
		this.resource('menu', { path: ':itemno'});
	});
	this.resource('cafe');
	this.resource('parking');
	this.resource('about');
	this.resource('eatout');
	this.resource('restaurant', { path: ':Rid'});
	this.resource('laddergame');
});

App.IndexRoute=Ember.Route.extend({
	beforeModel: function() {
		this.transitionTo('menus');
	}
});

App.NavigationView = Ember.View.extend({
	templateName: 'navigation',
	selectedBinding: 'controller.selected',
	NavItemView: Ember.View.extend({
		tagName: 'li',
		classNameBindings: 'isActive:active'.w(),
		isActive: function() {
			return this.get('item') === this.get('parentView.selected');
		}.property('item', 'parentView.selected').cacheable()
	})
});

App.MenusView=Ember.View.extend({
	click: function () {
		$("html, body").animate({ scrollTop: 0}, 600);
	}
});

var MenusData=getMenusData("menu/csv_export_utf8.csv");

App.MenusRoute = Ember.Route.extend({
	model: function() {
		return MenusData;
	},
	actions: {
		menunow: function () {
			this.transitionTo('menu',date2itemno());
		}
	},
});

App.MenuRoute = Ember.Route.extend({
	model: function(params) {
		return MenusData.findBy('itemno', params.itemno);
	}
});

App.RestaurantRoute = Ember.Route.extend({
	model: function(params) {
		var retVal=RestaurantsData.findBy('Rid', params.Rid);
		console.debug(retVal)
		return retVal;
	}
});

function getMenusData(Url) {
	var csv= showGetResult(Url);
	var retval=CSV2JSON(csv);
	var jsondata=$.parseJSON(retval);
	return jsondata;
};

var RestaurantsData=[
	{ 
		Rid:"1", 
		title: "명인만두", 
		tel:"0316967433", 
		img:"img/img_myoungin.png", 
		desc:[
			{line:"평일 07:30~21:00 / 일요일/공휴일 07:30~19:30 / 토요일: 휴무"}, 
			{line:"위치: H스퀘어"}, 
			{line:"메뉴: 분식류(6000원 한도)"}
		],
		menulist:[
			{item:"고기만두", price:"3,500"}, 
			{item:"김치만두", price:"3,500"}, 
			{item:"고기+김치", price:"	3,500"}, 
			{item:"군만두", price:"4,000"}, 
			{item:"모듬만두", price:"6,000"}, 
			{item:"고추만두", price:"4,000"}, 
			{item:"수제튀김만두", price:"3,000"}, 
			{item:"떡튀범벅", price:"5,500"}, 
			{item:"비빔만두", price:"6,000"}, 
			{item:"(떡)만두국", price:"6,000"},	 
			{item:"만두칼국수", price:"6,000"}, 
			{item:"얼큰만두국", price:"6,000"}, 
			{item:"만두잡채덮밥", price:"6,000"}, 
			{item:"고추김밥", price:"3,500"}, 
			{item:"새우김밥", price:"3,500"}, 
			{item:"참치김밥", price:"3,500"}, 
			{item:"모듬김밥", price:"3,500"}, 
			{item:"야채김밥", price:"2,500"}, 
			{item:"치즈김밥", price:"3,000"}, 
			{item:"오뎅(1인분", price:"2,000"}, 
			{item:"명인떡볶이", price:"3,500"}, 
			{item:"라면사리,오뎅, 계란, 만두", price:"각 1,000"}, 
			{item:"항아리수제비", price:"6,000"}, 
			{item:"짜장라볶이", price:"4,500"}, 
			{item:"해물볶음우동", price:"6500 ->6,000"},
			{item:"비빔국수", price:"5,500"}, 
			{item:"명인라면", price:"3,000"}, 
			{item:"떡사리, 만두, 김치, 치즈", price:"각 500"},
			{item:"해장라면", price:"4,500"}, 
			{item:"물냉면", price:"5,000"}, 
			{item:"비빔냉면", price:"5,000"}, 
			{item:"쫄면", price:"	5,000"}, 
			{item:"해물오믈렛", price:"6,000"}, 
			{item:"김치새우볶음밥", price:"6,000"}, 
			{item:"김치참치볶음밥", price:"6,000"}, 
			{item:"명인볶음밥", price:"6,000"}, 
			{item:"김치(참치)찌개", price:"5,000"},	
			{item:"순두부찌개", price:"5,000"}, 
			{item:"돈까스", price:"6,000"}, 
			{item:"매콤치킨까스", price:"6,500 -> 6,000"},
			{item:"고구마치즈돈까스", price:"6,500 -> 6,000"},
			{item:"된장찌개", price:"5,000"}, 
			{item:"제육덮밥", price:"6,500 -> 6,000 "},
			{item:"오징어덮밥", price:"6,000"}, 
			{item:"비빔밥", price:"5,000"}, 
			{item:"돌솥비빔밥", price:"6,000"}, 
			{item:"김치날치알돌솥밥", price:"6,000"}, 
			{item:"공기밥", price:"1,000"}, 
			{item:"모닝SET(오전11시까지) 라면+김밥+계란후라이", price:"5,000"},
			{item:"냉쫄면", price:"5,000"}, 
			{item:"냉모밀만두정식", price:"6,000"}, 
			{item:"콩국수", price:"6,000"},
		]
	},
	{ 
		Rid:"2", 
		title: "찌개애감동", 
		tel:"0316281479", 
		img:"img/img_jjigae.png", 
		desc:[
			{line:"평일 10:30~22:00 / 토요일/공휴일 10:30~15:00 / 일요일: 휴무"}, 
			{line:"위치: 유스페이스"},
			{line:"메뉴: 찌개류, 제육불고기"}
		],
		menulist:[
			{item:"차돌박이된장찌개", price:"7,000 -> 6,000"},
			{item:"시골된장찌개", price:"6,000"},
			{item:"옛날청국장찌개", price:"6,000"},
			{item:"해물순두부찌개", price:"6,000"},
			{item:"추억愛고추장찌개", price:"6,000"},
			{item:"참치김치찌개", price:"6,000"},
			{item:"옹기비빔밥", price:"6,000"},
			{item:"뚝불고기정식", price:"7,000 -> 6,000"},
			{item:"모짜렐라치즈계란말이", price:"6,000"},
			{item:"계란탕", price:"6,000"},
			{item:"제육불고기", price:"6,000"},
			{item:"생선구이(단품)", price:"6,000"},
		]
	},
	{ 
		Rid:"3", 
		title: "담소사골순대", 
		tel:"0317893821", 
		img:"img/img_damso.png", 
		desc:[
			{line:"평일 24시간 / 월요일 11:00 Open / 일요일: 22:30 Close"}, 
			{line:"위치: H스퀘어"}, 
			{line:"메뉴: 메뉴: 우사골돈순대국\우사골우순대국"}
		],
		menulist:[
			{item:"우사골돈순대국", price:"5,700"},
			{item:"우사골우순대국", price:"5,900"},
		]
	},
	{ 
		Rid:"4", 
		title: "the진국", 
		tel:"0317893555", 
		img:"img/img_jincook.png", 
		desc:[
			{line:"평일/토요일/공휴일 24시간 / 일요일: 22:30 Close"}, 
			{line:"위치: H스퀘어 S동"}, 
			{line:"메뉴: 수육국밥"}
		],
		menulist:[
			{item:"수육국밥", price:"6,000"}, 	
		]
	},
	{ 
		Rid:"5", 
		title: "신기소", 
		tel:"0317893737", 
		img:"img/img_singiso.png", 
		desc:[
			{line:"평일/주말,공휴일 11:00~21:00"}, 
			{line:"위치: H스퀘어 S동"}, 
			{line:"메뉴: 가쯔돈, 김치가쯔돈, 우동, 돌솥알밥, 김치우동, 회덥밥, 새우튀김우동)"}
		],
		menulist:[
			{item:"가쯔돈", price:"7,500 -> 6000"},
			{item:"김치가쯔돈", price:"7,500 -> 6000"},
			{item:"우동", price:"6,000"},
			{item:"김치우동", price:"7,000 -> 6,000"},
			{item:"돌솥알밥", price:"6,500 -> 6,000"},
			{item:"회덮밥", price:"6,500 -> 6000"},
			{item:"자루소바", price:"6,000"},
			{item:"새우튀김", price:"6,000"},
			{item:"새우튀김우동", price:"6,000"},
			{item:"타코야끼", price:"6,000"},
		]
	},
	{ 
		Rid:"6", 
		title: "Pho커리", 
		tel:"0317893514", 
		img:"img/img_phocurry.png", 
		desc:[
			{line:"평일 09:00~21:00 / 주말,공휴일 10:00~20:00"}, 
			{line:"위치: H스퀘어 S동"}, 
			{line:"메뉴: 돈까스커리, 양지쌀국수, 오무라이스"}
		],
		menulist:[
			{item:"돈까스커리", price:"6,000"},
			{item:"양지쌀국수", price:"6,000"},
			{item:"오무라이스", price:"6,000"},
		]
	},
	{ 
		Rid:"7", 
		title: "Rotutu", 
		tel:"0316967455", 
		img:"img/img_rotutu.png", 
		desc:[
			{line:"배달가능 / 평일 10:30~22:00 / 토요일/공휴일 11:00~22:00 / 일요일: 휴무"}, 
			{line:"위치: H스퀘어 S동"}, 
			{line:"메뉴: 햄에그샌드위치+음료, 햄치즈샌드위치+음료, 샌드위치: 스테이크/베이컨, 샐러드: 닭가슴살/리코타치즈"}
		],
		menulist:[
			{item:"햄에그샌드위치+음료", price: "6,500 -> 6,000"},
			{item:"햄치즈샌드위치+음료", price: "6,000"},
			{item:"스테이크샌드위치", price: "6,500 -> 6,000"},
			{item:"베이컨샌드위치", price: "6,500 -> 6,000"},
			{item:"닭가슴살샐러드", price: "8,000 -> 6,000"},
			{item:"리코타치즈샐러드", price: "7,000 -> 6,000"},
		]
	},
];

App.EatoutRoute = Ember.Route.extend({
	model: function() {
		return RestaurantsData;
	}
})

App.CafeController = Ember.Controller.extend({
	selectedMenuCount: null,
	selectedMenuItem: null,
	menusCount: [
		{ label: "1", value: "1"},
		{ label: "2", value: "2"},
		{ label: "3", value: "3"},
		{ label: "4", value: "4"},
		{ label: "5", value: "5"},
		{ label: "6", value: "6"},
		{ label: "7", value: "7"},
		{ label: "8", value: "8"},
		{ label: "9", value: "9"}
	],
	menusItem: [
		{ label: "에스프레소", value: "에스프레소" },
		{ label: "아메리카노", value: "아메리카노" },
		{ label: "카푸치노", value: "카푸치노" },
		{ label: "카라멜마키아또", value: "카라멜마키아또" },
		{ label: "카페모카", value: "카페모카" },
		{ label: "-------", value: null },
		{ label: "카페라떼", value: "카페라떼" },
		{ label: "바닐라라떼", value: "바닐라라떼" },
		{ label: "녹차라떼", value: "녹차라떼" },
		{ label: "고구마라떼", value: "고구마라떼" },
		{ label: "-------", value: null },
		{ label: "카모마일", value: "카모마일" },
		{ label: "페퍼민트", value: "페퍼민트" },
		{ label: "-------", value: null },
		{ label: "우유", value: "우유" },
		{ label: "아이스티", value: "아이스티" },
		{ label: "율무차", value: "율무차" },
		{ label: "유자차", value: "유자차" },
		{ label: "쌍화차", value: "쌍화차" },
		{ label: "-------", value: null },
		{ label: "오렌지쥬스", value: "오렌지쥬스" },
		{ label: "사과쥬스", value: "사과쥬스" },
		{ label: "자몽쥬스", value: "자몽쥬스" },
		{ label: "-------", value: null },
		{ label: "블루레몬에이드", value: "블루레몬에이드 "},
		{ label: "핑크레몬에이드", value: "핑크레몬에이드 "}
	],
	actions: {
		addCurrentOrder: function() {
			var selectedMenuItem=this.get('selectedMenuItem.value');
			var selectedMenuCount=this.get('selectedMenuCount.value');
			if (selectedMenuItem != null) {
				var OrderItem=({item:selectedMenuItem, count:selectedMenuCount});
				App.OrderComponent.create(OrderItem).appendTo($('#orderlisttable'));
			}
		}
	}
});

App.OrderComponent=Ember.Component.extend({
	templateName: "components/cafeOrders",
	didInsertElement: function(){
		console.debug('didInsertElement - OrderComponent');
	},
	actions: {
		remove: function() {
			this.remove();
		}
	}
});

var currentPP=getCookie("parkingplace");

App.ParkingController = Ember.Controller.extend({
	selectedParkingPlace: function () { 
		var parkingplace= getCookie("parkingplace");
		if (parkingplace=='') { return null };
		return parkingplace;
	},
	parkingPlaces: [
		{ label: "Reset", value: null},
		{ label: "B2", value: "B2"},
		{ label: "B3", value: "B3"},
		{ label: "B4", value: "B4"}
	],
	actions: {
		setParkingPlace: function() {
			var selectedParkingPlace=this.get('selectedParkingPlace.value');
			if (selectedParkingPlace != null) {
				var ParkingPlace=({parkingplace:selectedParkingPlace});
				App.ParkingComponent.create(ParkingPlace).appendTo($('#parkingTable'));
				console.debug(selectedParkingPlace);
				setCookie("parkingplace", selectedParkingPlace, 2, "926.1kko.com");
			} else {
				delCookie("parkingplace");
			}
		}
	},
	current: currentPP
});


App.ParkingComponent=Ember.Component.extend({
	templateName: "components/Parking",
	didInsertElement: function(){
		console.debug('didInsertElement - ParkingComponent');
	},
	actions: {
		remove: function() {
			this.remove();
		}
	}
});


Ember.Handlebars.helper('format-date', function(date){
	var d = new Date(date);
	var week = new Array('일', '월', '화', '수', '목', '금', '토');
	var str=(week[d.getDay()]);
	return new Ember.Handlebars.SafeString(date+' ('+str+')');
});

Ember.Handlebars.helper('format-tel', function(tel){
	var retVal=tel.substr(0,3)+'-'+tel.substr(3,3)+'-'+tel.substr(6,4);
	return retVal;
});

function getCookie( name ) {
	var start = document.cookie.indexOf( name + "=" );
	var len = start + name.length + 1;
	if ( ( !start ) && ( name != document.cookie.substring( 0, name.length ) ) ) {
		return null;
	}
	if ( start == -1 ) return null;
	var end = document.cookie.indexOf( ';', len );
	if ( end == -1 ) end = document.cookie.length;
	return unescape( document.cookie.substring( len, end ) );
}
 
function setCookie( name, value, expires, path, domain, secure ) {
	var today = new Date();
	today.setTime( today.getTime() );
	if ( expires ) {
		expires = expires * 1000 * 60 * 60 * 24;
	}
	var expires_date = new Date( today.getTime() + (expires) );
	document.cookie = name+'='+escape( value ) +
		( ( expires ) ? ';expires='+expires_date.toGMTString() : '' ) + //expires.toGMTString()
		( ( path ) ? ';path=' + path : '' ) +
		( ( domain ) ? ';domain=' + domain : '' ) +
		( ( secure ) ? ';secure' : '' );
}
 
function delCookie( name, path, domain ) {
	if ( getCookie( name ) ) document.cookie = name + '=' +
			( ( path ) ? ';path=' + path : '') +
			( ( domain ) ? ';domain=' + domain : '' ) +
			';expires=Thu, 01-Jan-1970 00:00:01 GMT';
}

// Source: http://www.bennadel.com/blog/1504-Ask-Ben-Parsing-CSV-Strings-With-Javascript-Exec-Regular-Expression-Command.htm
// This will parse a delimited string into an array of
// arrays. The default delimiter is the comma, but this
// can be overriden in the second argument.

function CSVToArray(strData, strDelimiter) {
	// Check to see if the delimiter is defined. If not,
	// then default to comma.
	strDelimiter = (strDelimiter || ",");
	// Create a regular expression to parse the CSV values.
	var objPattern = new RegExp((
	// Delimiters.
	"(\\" + strDelimiter + "|\\r?\\n|\\r|^)" +
	// Quoted fields.
	"(?:\"([^\"]*(?:\"\"[^\"]*)*)\"|" +
	// Standard fields.
	"([^\"\\" + strDelimiter + "\\r\\n]*))"), "gi");
	// Create an array to hold our data. Give the array
	// a default empty first row.
	var arrData = [[]];
	// Create an array to hold our individual pattern
	// matching groups.
	var arrMatches = null;
	// Keep looping over the regular expression matches
	// until we can no longer find a match.
	while (arrMatches = objPattern.exec(strData)) {
		// Get the delimiter that was found.
		var strMatchedDelimiter = arrMatches[1];
		// Check to see if the given delimiter has a length
		// (is not the start of string) and if it matches
		// field delimiter. If id does not, then we know
		// that this delimiter is a row delimiter.
		if (strMatchedDelimiter.length && (strMatchedDelimiter != strDelimiter)) {
			// Since we have reached a new row of data,
			// add an empty row to our data array.
			arrData.push([]);
		}
		// Now that we have our delimiter out of the way,
		// let's check to see which kind of value we
		// captured (quoted or unquoted).
		if (arrMatches[2]) {
			// We found a quoted value. When we capture
			// this value, unescape any double quotes.
			var strMatchedValue = arrMatches[2].replace(
			new RegExp("\"\"", "g"), "\"");
		} else {
			// We found a non-quoted value.
			var strMatchedValue = arrMatches[3];
		}
		// Now that we have our value string, let's add
		// it to the data array.
		arrData[arrData.length - 1].push(strMatchedValue);
	}
	// Return the parsed data.
	return (arrData);
}

function CSV2JSON(csv) {
	var array = CSVToArray(csv);
	var objArray = [];
	for (var i = 1; i < array.length-1; i++) {
		objArray[i - 1] = {'itemno':String(i)};
		for (var k = 0; k < array[0].length && k < array[i].length; k++) {
			var key = array[0][k];
			objArray[i - 1][key] = array[i][k]
		}
	}
	var json = JSON.stringify(objArray);
	var str = json.replace(/},/g, "},\r\n");

	return json;
}

function showGetResult( Url )
{
	var result = null;
	$.ajax({
		url: Url,
		type: 'get',
		//contentType: "text/csv; charset=MS949",  // KSC5601 8859_1 ascii UTF-8 EUC-KR MS949
		//charset: 'MS949',
		dataType: 'html',
		async: false,
		success: function(data) {
			result = data;
			result = result.replace('시작 날짜','start_date');
			result = result.replace('시작 시간','start_time');
			result = result.replace('끝 날짜','end_date');
			result = result.replace('끝 시간','end_time');
			result = result.replace('제목','subject');
			result = result.replace('설명','description');
		}
	});
	return result;
}

function date2itemno()
{
	var now = new Date();
	var week = new Array(99, 1, 4, 7, 10, 13, 99);
	var base = (week[now.getDay()]);

	var hour= now.getHours();
	if (hour >= 9) { base+=1; };
	if (hour >=13) { base+=1; };
	if (base >=16) { base=1; };
	return String(base);
}

function displayGoogleAds() {
	$('ins').each(function(){
		(adsbygoogle = window.adsbygoogle || []).push({});
	});
}
