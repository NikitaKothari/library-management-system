app.controller('checkoutByBookCtrl', ['$scope', 'libraryService', '$mdDialog',
    "$location", "$window", function($scope, libraryService, $mdDialog, $location, $window) {
      $scope.isbn = $location.search().isbn;
      $scope.book = {};
      $scope.borrower = {};
      $scope.isLoading = true;

      libraryService.getBook($scope.isbn).then(function(data) {
        $scope.book = data;
        $scope.bookLoans = {};
        $scope.borrower = {};
        $scope.isLoading = false;
        for (let bookLoans of $scope.book.bookLoans) {
          if (bookLoans.dateIn == "1969-12-31") {
            $scope.bookLoans = bookLoans;
            $scope.borrower = bookLoans.borrower;
            break;
          }
        }
      });

      $scope.checkOut = function() {
        $scope.book.bookLoans = [];
        $scope.bookLoans.book = $scope.book;
        libraryService.updateBookLoans($scope.bookLoans.loanId).then(function(data) {
          if (data) {
            if(data.fine && data.fine.fineAmt > 0){
              $scope.showForm("#confirmDialogCheckOutBookPayDue");
            }else{
              $scope.showForm("#confirmDialogCheckOutBook");
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
          contentElement: '#confirmDialogCheckOutBook',
          skipHide: true,
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose: true
        });
      };
      
    }])