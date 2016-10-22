app.controller('checkoutByBorrowerCtrl', [
    '$scope',
    'libraryService',
    '$mdDialog',
    "$location",
    "$window",
    function($scope, libraryService, $mdDialog, $location, $window) {
      $scope.cardId = $location.search().cardId;
      $scope.books = [];
      $scope.borrower = {};
      $scope.isLoading = true;

      libraryService.getByCardId($scope.cardId).then(function(data) {
        $scope.borrower = data;
        $scope.books = [];
        for (let bookLoans of $scope.borrower.bookLoans) {
          if (bookLoans.dateIn == "1969-12-31") {
            $scope.books.push(bookLoans.book);
          }
        }
        $scope.isLoading = false;
      });

      $scope.checkOut = function(loanId) {
        libraryService.updateBookLoans(loanId).then(
                function(data) {
                  if (data) {
                    if(data.fine && data.fine.fineAmt > 0){
                      $scope.showForm("#confirmDialogCheckOutBorrowerPayDue");
                    }else{
                      $scope.showForm("#confirmDialogCheckOutBorrower");
                    }
                  }
                });
      }
      
      $scope.payDue = function() {
        var url = "http://" + $window.location.host
          + "/library/#/payDue?cardId=" + $scope.borrower.cardId;
        $window.location.href = url;
        $mdDialog.hide();
      }

      $scope.goToHome = function() {
        var url = "http://" + $window.location.host + "/library/#/";
        $window.location.href = url;
        $mdDialog.hide();
      };

      $scope.showForm = function(ev) {
        $mdDialog.show({
          contentElement: ev,
          skipHide: true,
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose: true
        });
      };

      $scope.ok = function() {
        $scope.isLoading = true;
        $mdDialog.hide();
        libraryService.getByCardId($scope.cardId).then(function(data) {
          $scope.borrower = data;
          $scope.books = [];
          for (let bookLoans of $scope.borrower.bookLoans) {
            if (bookLoans.dateIn == "1969-12-31") {
              $scope.books.push(bookLoans.book);
            }
          }
          $scope.isLoading = false;
        });
      }

    }])