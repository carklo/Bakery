/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("nationalityModule");

    mod.controller("nationalityDeleteCtrl", ["$state", "nationality", function ($state, nationality) {
            this.confirmDelete = function () {
                nationality.remove().then(function () {
                    $state.go('nationalityList', null, {reload: true});
                });
            };
        }]);
})(window.angular);

