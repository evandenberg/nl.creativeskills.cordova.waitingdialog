/*global module,cordova,require */

var cordova = require('cordova'),
    exec = require('cordova/exec'),
    waitingDialog;

(function () {
    'use strict';

	function WaitingDialog() {
	}

	WaitingDialog.prototype.show = function(text) {
		exec(null, null, "WaitingDialog", "show", [text]);
	};

	WaitingDialog.prototype.hide = function() {
		exec(null, null, "WaitingDialog", "hide", []);
	};

	waitingDialog = new WaitingDialog();

	module.exports = waitingDialog;

}());