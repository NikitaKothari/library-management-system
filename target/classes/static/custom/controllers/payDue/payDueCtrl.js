app.controller('payDueCtrl', ['$scope', 'libraryService', '$mdDialog',
    "$location", "$window", function($scope, libraryService, $mdDialog, $location, $window) {
      $scope.cardId = $location.search().cardId;
      $scope.borrower = {};
      $scope.isLoading = true;
      $scope.fineAmt = 0;
      
      libraryService.getByCardId($scope.cardId).then(function(data) {
        $scope.borrower = data;
        var bookLoans = $scope.borrower.bookLoans;
        for(var i =0; i< bookLoans.length; i++){
          if(bookLoans[i].fine != null && bookLoans[i].fine.fineAmt > 0){
            $scope.fineAmt = $scope.fineAmt + bookLoans[i].fine.fineAmt;
          }
        }
        $scope.isLoading = false;
      });

      $scope.payDue = function() {
        libraryService.payDue($scope.cardId).then(function(data) {
          if (data) {
            $scope.showForm();
          }
        });
      }

      $scope.goToHome = function() {
        var url = "http://" + $window.location.host + "/library/#/";
        $window.location.href = url;
        $mdDialog.hide();
      };

      $scope.showForm = function(ev) {
        $mdDialog.show({
          contentElement: '#confirmDialogPayDue',
          skipHide: true,
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose: true
        });
      };

    }])