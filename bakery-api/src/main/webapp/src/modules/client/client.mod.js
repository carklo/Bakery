/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {
    var mod = ng.module('clientModule', ['ngCrud', 'ui.router']);

    mod.constant('clientModel', {
        name: 'client',
        displayName:  'Client',
        url: 'clients',
        fields: {
            name: {
                
                displayName: 'Name',
                type:  'String',
                required: true 
            },
            lastName: 
             {
                        displayName: 'Lastname',
                        type: 'String',
                        required: true
                    },
            nationality: {
                displayName: 'Nationality',
                type: 'Reference',
                model: 'nationalityModel',
                options: [],
                required: true
            }        
        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/client/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('client', {
                url: '/clients?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'client.tpl.html',
                        controller: 'clientCtrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                nationality: r.all('nationalitys').getList()
                            });
                        }],
                    model: 'clientModel',
                    clients: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('clientList', {
                url: '/list/:name',
                parent: 'client',
                views: {
                     clientView: {
                        templateUrl: basePath + 'list/client.list.tpl.html',
                        controller: 'clientListCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                 resolve:{
                        model: 'clientModel',
                        client: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]                
					},
                 ncyBreadcrumb: {
                   label: 'client'
                    }
            });
            $sp.state('clientNew', {
                url: '/new',
                parent: 'client',
                views: {
                    clientView: {
                        templateUrl: basePath + 'new/client.new.tpl.html',
                        controller: 'clientNewCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                  resolve:{
						model: 'clientModel'
					},
                  ncyBreadcrumb: {
                        parent :'clientList', 
                        label: 'new'
                   }
            });
            $sp.state('clientInstance', {
                url: '/{clientId:int}',
                abstract: true,
                parent: 'client',
                views: {
                    clientView: {
                        template: '<div ui-view="clientInstanceView"></div>'
                    }
                },
                resolve: {
                    client: ['clients', '$stateParams', function (clients, $params) {
                            return clients.get($params.clientId);
                        }]
                }
            });
            $sp.state('clientDetail', {
                url: '/details',
                parent: 'clientInstance',
                views: {
                    clientInstanceView: {
                        templateUrl: baseInstancePath + 'detail/client.detail.tpl.html',
                        controller: 'clientDetailCtrl'
                    }
                },
                  resolve:{
						model: 'clientModel'
					},
                  ncyBreadcrumb: {
                        parent :'clientList', 
                        label: 'details'
                    }
            });
            $sp.state('clientEdit', {
                url: '/edit',
                sticky: true,
                parent: 'clientInstance',
                views: {
                    clientInstanceView: {
                        templateUrl: baseInstancePath + 'edit/client.edit.tpl.html',
                        controller: 'clientEditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                  resolve:{
						model: 'clientModel'
					},
                  ncyBreadcrumb: {
                        parent :'clientDetail', 
                        label: 'edit'
                    }
            });
            $sp.state('clientDelete', {
                url: '/delete',
                parent: 'clientInstance',
                views: {
                    clientInstanceView: {
                        templateUrl: baseInstancePath + 'delete/client.delete.tpl.html',
                        controller: 'clientDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                  resolve:{
				      model: 'clientModel'
					}
            });
	}]);
})(window.angular);