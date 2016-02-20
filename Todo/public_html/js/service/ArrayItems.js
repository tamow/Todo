(function (app) {
    'use strict';
    var ArrayItems = function ($scope) {
        this.init($scope);
    };

    var p = ArrayItems.prototype;

    p.init = function ($scope) {
        this.$scope = $scope;
        this.items = new Array();
    };

    p.setCurrentItem = function (item) {
        this.current = item;
    };

    p.getCurrentItem = function () {
        return this.current;
    };

    p.list = function (callback) {
        callback.call(this, this.items);
    };

    p.add = function (item, callback) {
        this.items.push(item);
        //  データ変更通知
        this.$scope.$broadcast('changeItems');
        callback.call(this, item);
    };

    p.update = function (item, callback) {
        for (var i = 0; i < this.items.length; i++) {
            if (item.id === this.items[i].id) {
                this.items[i] = item;
                break;
            }
        }
        this.$scope.$broadcast('changeItems');
        callback.call(this, item);
    };

    p.remove = function (item, callback) {
        var tmp = new Array();
        for (var i = 0; i < this.items.length; i++) {
            if (item.id !== this.items[i].id) {
                tmp.push(this.items[i]);
            }
        }
        this.items = tmp;
        this.$scope.$broadcast('changeItems');
        callback.call(this, item);
    };

    app.ArrayItems = ArrayItems;

}(this.app));
