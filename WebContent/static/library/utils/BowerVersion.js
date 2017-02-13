// 获取浏览器名称和版本
function getBrowserInfo() {
	var userAgent = navigator.userAgent,   
	rMsie = /(msie\s|trident.*rv:)([\w.]+)/,   
	rFirefox = /(firefox)\/([\w.]+)/,   
	rOpera = /(opera).+version\/([\w.]+)/,   
	rChrome = /(chrome)\/([\w.]+)/,   
	rSafari = /version\/([\w.]+).*(safari)/;  
	var browser;  
	var version;  
	var ua = userAgent.toLowerCase();  
	  var match = rMsie.exec(ua);  
	  if(match != null){  
	    return { browser : "IE", version : match[2] || "0" };  
	  }  
	  var match = rFirefox.exec(ua);  
	  if (match != null) {  
	    return { browser : match[1] || "", version : match[2] || "0" };  
	  }  
	  var match = rOpera.exec(ua);  
	  if (match != null) {  
	    return { browser : match[1] || "", version : match[2] || "0" };  
	  }  
	  var match = rChrome.exec(ua);  
	  if (match != null) {  
	    return { browser : match[1] || "", version : match[2] || "0" };  
	  }  
	  var match = rSafari.exec(ua);  
	  if (match != null) {  
	    return { browser : match[2] || "", version : match[1] || "0" };  
	  }  
	  if (match != null) {  
	    return { browser : "", version : "0" };  
	  }  
}

