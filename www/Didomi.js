var exec = require('cordova/exec');
var PLUGIN_NAME = "CDVDidomi";

var CDVDidomi ={ 
initialize : function (key, noticeId, successCallback, errorCallback) { 
    console.log("initialize : exec CDVDidomi - initialize");
    exec(successCallback, errorCallback, PLUGIN_NAME, 'initialize', [key,noticeId]); 
}, 
isReady : function () { 
    return exec(()=>{}, ()=>{}, PLUGIN_NAME, 'isReady', []);
},
showNotice : function (successCallback, errorCallback) { 
    exec(successCallback, errorCallback, PLUGIN_NAME, 'showNotice', []);
},
showPreferences : function () { 
    exec(()=>{}, ()=>{}, PLUGIN_NAME, 'showPreferences', []);
},
shouldUserStatusBeCollected : function(successCallback) { 
    exec(successCallback, ()=>{}, PLUGIN_NAME, "shouldUserStatusBeCollected", []); 
},
setUserAgreeToAll : function() {
    exec(()=>{}, ()=>{}, PLUGIN_NAME, "setUserAgreeToAll", []);
},
setUserDisagreeToAll : function() {
    exec(()=>{}, ()=>{}, PLUGIN_NAME, "setUserDisagreeToAll", []);
},
};
if (typeof module != 'undefined' && module.exports) {
    module.exports = CDVDidomi;
}