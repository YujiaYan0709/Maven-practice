'use strict';

bottomApp.service('BottomService', ['$http', 'URI', 'promiseWrapper', function ($http, URI, promise) {

    var bottomsUri = URI.resources.bottoms;

    this.toViewModel = function (bottomFromApi) {
        return {
            id: bottomFromApi.id,
            name: bottomFromApi.name,
            facture: bottomFromApi.facture,
            location: bottomFromApi.location,
            createTime: bottomFromApi.createTime,
            updateTime: bottomFromApi.updateTime
        }
    };

    this.retrieveBottoms = function (params) {
        return promise.wrap($http.get(bottomsUri, {params: params}));
    };

    this.getBottomInfoById = function (bottomId) {
        return promise.wrap($http.get(bottomsUri + '/' + bottomId));
    };

    this.createBottom = function (bottom) {
        return promise.wrap($http.post(bottomsUri, bottom));
    };

    this.modifyBottom = function (bottom) {
        return promise.wrap($http.put(bottomsUri + "/" + bottom.id, bottom));
    };

    this.deleteBottom = function (bottom) {
        return promise.wrap($http.delete(bottomsUri + "/" + bottom.id));
    };

}]);

