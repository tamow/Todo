(function (module) {
    'use strict';

    module.controller('pageController', function ($scope, $timeout, Items) {

        $scope.show = {
            list: true,
            add: false,
            info: false,
            update: false
        };

        $scope.message = {
            type: 'alert-info',
            text: '',
            show: false
        };

        $scope.changePage = function (type) {
            for (var name in $scope.show) {
                if (name === type) {
                    $scope.show[name] = true;
                } else {
                    $scope.show[name] = false;
                }
            }
            if (type === 'info') {
                var item = Items.getCurrentItem();
                $scope.active = item;
            } else if (type === 'update') {
                var item = Items.getCurrentItem();
                $scope.edit = item;
            }
        };

        $scope.deleteItem = function () {
            var item = Items.getCurrentItem();
            Items.remove(item, function () {
                $scope.changePage('list');
                $scope.showMessage({
                    type: 'alert-warning',
                    text: '削除しました',
                    show: true
                });
            });
        };

        $scope.showMessage = function (msg) {
            $scope.message = msg;
            $timeout(function () {
                $scope.message.show = false;
            }, 3000);
        };
    });

    module.controller('listController', function ($scope, Items) {

        $scope.items = [];

        Items.list(function (list) {
            $scope.items = list;
        });

        $scope.show = function (item) {
            Items.setCurrentItem(item);
            $scope.$parent.changePage('info');
        };

        $scope.$on('changeItems', function () {
            Items.list(function (list) {
                $scope.items = list;
            });
        });
    });

    module.controller('addController', function ($scope, Items) {

        $scope.item = {
        };

        $scope.addItem = function () {
            if (!$scope.addItemForm.$valid) {
                return;
            }
            Items.add($scope.item, function () {
                $scope.$parent.showMessage({
                    type: 'alert-info',
                    text: '追加しました',
                    show: true
                });
                $scope.$parent.changePage('list');
                $scope.item = {};

            });
        };
    });

    module.controller('updateController', function ($scope, Items) {

        $scope.updateItem = function () {
            if (!$scope.updateItemForm.$valid) {
                return;
            }
            Items.update($scope.$parent.edit, function () {
                $scope.$parent.showMessage({
                    type: 'alert-success',
                    text: '更新しました',
                    show: true
                });
                $scope.$parent.changePage('list');
                $scope.$parent.edit = {};

            });
        };
    });

}(TodoModule));