/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("nationalityModule");

    mod.controller("nationalityEditCtrl", ["$scope", "$state", "nationality",
        function ($scope, $state, nationality) {
            $scope.currentRecord = nationality;
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.nationalityForm.$valid) {
                            $scope.currentRecord.put().then(function (rc) {
                                $state.go('nationalityDetail', {nationalityId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('nationalityDetail');
                    }
                }
            };
        }]);
})(window.angular);

