/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("nationalityModule");

    mod.controller("nationalityListCtrl", ["$scope", '$state', 'nationalitys', '$stateParams',
        function ($scope, $state, nationalitys, $params) {
            $scope.records = nationalitys;

            //Paginación
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            this.totalItems = nationalitys.totalRecords;

            this.pageChanged = function () {
                $state.go('nationalityList', {page: this.currentPage});
            };

            $scope.actions = {
                create: {
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('nationalityNew');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                }            };
            $scope.recordActions = {
                detail: {
                    displayName: 'Detail',
                    icon: 'eye-open',
                    fn: function (rc) {
                        $state.go('nationalityDetail', {nationalityId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function (rc) {
                        $state.go('nationalityEdit', {nationalityId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function (rc) {
                        $state.go('nationalityDelete', {nationalityId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                }
            };
        }]);
})(window.angular);

