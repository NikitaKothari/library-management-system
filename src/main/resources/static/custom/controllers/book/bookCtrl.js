app.controller('bookCtrl', ['$scope', 'libraryService', '$mdDialog',
    "$location", '$window',
    function($scope, libraryService, $mdDialog, $location, $window) {
      $scope.isbn = $location.search().isbn;
      $scope.book = {};
      $scope.isLoading = true;
      $scope.isCheckInPossible = true;
      $scope.isCheckInPossibleOverDue = true;
      var noOfBooks = 0;

      libraryService.getBook($scope.isbn).then(function(data) {
        $scope.book = data;
        $scope.isLoading = false;
      });

      $scope.checkIn = function(cardId) {
        libraryService.getByCardId(cardId).then(function(data) {
          if(data){
            $scope.borrower = data;
            $scope.showError = false;
            noOfBooks = 0;
            $scope.isCheckInPossibleOverDue = true;
            for (let bookLoans of $scope.borrower.bookLoans) {
              if(new Date(bookLoans.dueDate).getTime() < new Date().getTime()){
                $scope.isCheckInPossibleOverDue = false;
              }
              if (bookLoans.dateIn == "1969-12-31") {
               noOfBooks ++;
              }
            }
            if(noOfBooks == 3){
              $scope.isCheckInPossible = false;
            }else{
              $scope.isCheckInPossible = true;
            }
            
          }else{
            $scope.showError = true;
          }
        });
      }

      $scope.confirm = function() {
        $scope.book.bookLoans = [];
        $scope.borrower.bookLoans = [];
        var bookLoans = {
          "loanId": 10,
          "dueDate": new Date().getTime() + 14 * 24 * 60 * 60,
          "dateOut": new Date().getTime(),
          "dateIn": null,
          "book": $scope.book,
          "borrower": $scope.borrower,
          "fine": null
        };

        libraryService.addBookLoans(bookLoans).then(function(data) {
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
          contentElement: '#confirmDialog',
          skipHide: true,
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose: true
        });
      };

    }])