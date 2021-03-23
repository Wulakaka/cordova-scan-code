var exec = require('cordova/exec');

exports.getCode = function (arg0, success, error) {
    exec(success, error, 'ScanCode', 'getCode', [arg0]);
};
exports.stopCode = function (arg0, success, error) {
    exec(success, error, 'ScanCode', 'stopCode', [arg0]);
};
