var url = location.search;
var request = new Object();
if(url.indexOf("?") != -1) {
	var str = url.substr(1)　 //去掉?号
		　　 strs = str.split("&");
	for(var i = 0; i < strs.length; i++) {　
		request[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
	}
}