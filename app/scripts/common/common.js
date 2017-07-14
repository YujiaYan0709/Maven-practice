'use strict';

var commonModule = angular.module('CommonModule', []);


commonModule.service('rootService', [function () {

    var self = this;
    self.activeNav = {};

    self.updateActiveNavLv1 = function (navHeaderItem) {
        self.activeNav.lv1 = navHeaderItem;
    };

    self.updateActiveNavL2 = function (menuItem) {
        self.activeNav.lv2 = menuItem;
    };

    self.updateActiveNavL3 = function (subMenuItem) {
        self.activeNav.lv3 = subMenuItem;
    };

    self.getActiveNav = function () {
        return self.activeNav;
    };

}]);

commonModule.service('sessionStorage', ['$window', function ($window) {

    var KEY = "session_createModel",
        self = this;

    var getDataFromSessionStorage = function () {
        return angular.fromJson($window.sessionStorage.getItem(KEY)) || {};
    };

    var saveDataFromSessionStorage = function (data) {
        $window.sessionStorage.setItem(KEY, angular.toJson(data));
    };

    self.put = function (id, text) {
        var data = getDataFromSessionStorage();
        data[id] = text;
        saveDataFromSessionStorage(data);
    };

    self.get = function (id) {
        var data = getDataFromSessionStorage();
        if (data[id]) {
            return data[id];
        } else {
            return null;
        }
    };

    self.remove = function (id) {
        var data = getDataFromSessionStorage();
        if (data.hasOwnProperty(id)) {
            delete  data[id];
            saveDataFromSessionStorage(data);
        }
    };
}]);

commonModule.service('localStorage', ['$window', function ($window) {

    //todo replace with log in user id as local storage key
    var KEY = "loginUserId" + "_createModel",
        self = this;

    var getDataFromLocalStorage = function () {
        return angular.fromJson($window.localStorage.getItem(KEY)) || {};
    };

    var saveDataFromLocalStorage = function (data) {
        $window.localStorage.setItem(KEY, angular.toJson(data));
    };

    self.put = function (id, text) {
        var data = getDataFromLocalStorage();
        data[id] = text;
        saveDataFromLocalStorage(data);
    };

    self.get = function (id) {
        var data = getDataFromLocalStorage();
        if (data[id]) {
            return data[id];
        } else {
            return null;
        }
    };

    self.remove = function (id) {
        var data = getDataFromLocalStorage();
        if (data.hasOwnProperty(id)) {
            delete  data[id];
            saveDataFromLocalStorage(data);
        }
    };
}]);

commonModule.service('promiseWrapper', ['$q', '$rootScope', function ($q, $rootScope) {
    var self = this;

    self.wrap = function (request) {
        var defer = $q.defer();
        request.success(function (data) {
            defer.resolve(data)
        }).error(function (data) {
            defer.reject(data)
        });
        return defer.promise;
    };

    self.mockWrap = function (data) {
        var defer = $q.defer();
        defer.resolve(data);
        return defer.promise;
    };

    self.wrapWithRootScopeLoading = function (request) {
        var defer = $q.defer();
        $rootScope.rootScopeLoading = true;
        request.success(function (data) {
            $rootScope.rootScopeLoading = false;
            defer.resolve(data)
        }).error(function (data) {
            $rootScope.rootScopeLoading = false;
            defer.reject(data)
        });
        return defer.promise;
    }

}]);
