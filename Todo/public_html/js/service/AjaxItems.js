(function (app) {
    'use strict';
    var AjaxItems = function ($scope, $http) {
        this.init($scope, $http);
    };
    var p = AjaxItems.prototype;

    p.init = function ($scope, $http) {
        this.$http = $http;
        this.$scope = $scope;
    };

    p.setCurrentItem = function (item) {
        this.current = item;
    };

    p.getCurrentItem = function () {
        return this.current;
    };

    p.list = function (callback) {
        this.$http({
            method: 'GET',
            url: 'http://localhost:8080/todo/api/list/'
        }).success(function (data) {
            callback.call(this, data);
        });
    };

    p.add = function (item, callback) {
        var $scope = this.$scope;
        this.$http({
            method: 'POST',
            url: 'http://localhost:8080/todo/api/',
            data: {
                title: item.title,
                memo: item.memo
            }
        }).success(function (response) {
            //  データが変更されたことを通知
            $scope.$broadcast('changeItems');
            callback.call(this, response.data);
        });
    };

    p.update = function (item, callback) {
        var $scope = this.$scope;
        this.$http({
            method: 'PUT',
            url: 'http://localhost:8080/todo/api/',
            data: {
                id: item.id,
                title: item.title,
                memo: item.memo
            }
        }).success(function (response) {
            $scope.$broadcast('changeItems');
            callback.call(this, response.data);
        });
    };

    p.remove = function (item, callback) {
        var $scope = this.$scope;
        this.$http({
            method: 'DELETE',
            url: 'http://localhost:8080/todo/api/' + item.id
        }).success(function (response) {
            $scope.$broadcast('changeItems');
            callback.call(this, response);
        });
    };

    app.AjaxItems = AjaxItems;
}(this.app));
