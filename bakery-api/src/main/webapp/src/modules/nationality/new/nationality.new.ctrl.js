/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("nationalityModule");

    mod.controller("nationalityNewCtrl", ["$scope", "$state", "nationalitys",
        function ($scope, $state, nationalitys) {
            $scope.currentRecord = {};
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.nationalityForm.$valid) {
                            nationalitys.post($scope.currentRecord).then(function (rc) {
                                $state.go('nationalityDetail', {nationalityId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('nationalityList');
                    }
                }
            };
        }]);
})(window.angular);

