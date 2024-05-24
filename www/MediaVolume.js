var exec = require('cordova/exec');

exports.getDeviceMediaVolume = function (success, error) {

    return exec(success, error, 'MediaVolume', 'getDeviceMediaVolume');
};
