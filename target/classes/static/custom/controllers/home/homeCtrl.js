app.controller('homeCtrl', [
    '$scope',
    'libraryService',
    '$mdDialog',
    '$window',
    function($scope, libraryService, $mdDialog, $window) {
      $scope.searchText = "";
      $scope.operation = "search";

      $scope.search = function() {
        if ($scope.searchText && $scope.searchText.length > 0) {
          var url = "http://" + $window.location.host + "/library/#/search?text="
                  + $scope.searchText;
          $window.location.href = url;
        } else {
          $scope.isSearchEmpty = true;
        }
      }
      
      
      $scope.searchByTitle = function(title) {
        if (title && title.length > 0) {
          var url = "http://" + $window.location.host + "/library/#/search?title="
                  + title;
          $window.location.href = url;
        } else {
          $scope.isSearchEmpty = true;
        }
      }
      
      $scope.searchByAutherName = function(autherName) {
        if (autherName && autherName.length > 0) {
          var url = "http://" + $window.location.host + "/library/#/search?autherName="
                  + autherName;
          $window.location.href = url;
        } else {
          $scope.isSearchEmpty = true;
        }
      }
      
      $scope.searchByKey = function(key) {
        if (key && key.length > 0) {
          var url = "http://" + $window.location.host + "/library/#/search?key="
                  + key;
          $window.location.href = url;
        } else {
          $scope.isSearchEmpty = true;
        }
      }

      $scope.checkoutByIsbn = function(isbn) {
        if(isbn && isbn.length > 0){
          libraryService.getBook(isbn).then(
                  function(data) {
                    if(data){
                      $scope.books = data;
                      if (!$scope.books.avalaible) {
                        var url = "http://" + $window.location.host
                                + "/library/#/checkoutByBook?isbn=" + isbn;
                        $window.location.href = url;
                      } else {
                        $scope.showErrorForBook = true;
                      }
                    }else{
                      $scope.showErrorForInvalidBook = true;
                    }
                  });
        }else{
          $scope.showErrorForInvalidBook = true;
        }
        
      }

      $scope.checkoutByCardId = function(cardId) {
        if (cardId && cardId.length > 0) {
          libraryService.getByCardId(cardId).then(
                  function(data) {
                    if(data){
                      $scope.borrower = data;
                      var checkedBooks = 0;
                      for ( let bookLoans of $scope.borrower.bookLoans) {
                        if (bookLoans.dateIn == "1969-12-31") {
                          checkedBooks++;
                          break;
                        }
                      }
                      if (checkedBooks > 0) {
                        var url = "http://" + $window.location.host
                                + "/library/#/checkoutByBorrower?cardId=" + cardId;
                        $window.location.href = url;
                      } else {
                        $scope.showBorrowerError = true;
                      }
                    }else{
                      $scope.showBorrowerInValidError = true;
                    }
                  });
        } else {
          $scope.showBorrowerInValidError = true;
        }

      }

      $scope.payDue = function(cardId) {
        if(cardId && cardId.length > 0){
          libraryService.getByCardId(cardId).then(
                  function(data) {
                    if(data){
                      $scope.borrower = data;
                      var fineAmt = 0;
                      for (let bookLoans of $scope.borrower.bookLoans) {
                        var fine = bookLoans.fine;
                        if (fine && fine.fineAmt > 0) {
                          fineAmt = fine.fineAmt;
                          break;
                        }
                      }
                      if (fineAmt) {
                        var url = "http://" + $window.location.host
                                + "/library/#/payDue?cardId=" + cardId;
                        $window.location.href = url;
                      } else {
                        $scope.showPayDueError = true;
                      }
                    }else{
                      $scope.showPayDueInvalidError = 0;
                    }
                  });
        }else{
          $scope.showPayDueInvalidError = 0;
        }
      }

      $scope.book = {};
      $scope.borrower = {};

      $scope.addBookClick = function() {
        $scope.showForm();
        $scope.book = {};
      }

      $scope.showForm = function(ev) {
        $mdDialog.show({
          contentElement: '#addBook',
          skipHide: true,
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose: true
        });
      };

      $scope.addBook = function() {
        $scope.book.bookLoans = [];
        var authors = $scope.book.authers.split(",");
        $scope.book.authers = [];
        for ( var author in authors) {
          $scope.book.authers.push({
            "name": author
          });
        }
        libraryService.addBook($scope.book).then(function(data) {
          if(data.isbn == null){
            $scope.isDuplicateBook = true;
            $scope.book.authers = data.authers;
          }else{
            $scope.bookForm.$setPristine();
            $scope.isDuplicateBook = false;
            $scope.book = {};
            $mdDialog.hide();
          }
        });
      }

      $scope.addBorrowerClick = function() {
        $scope.showFormBorrower();
        $scope.borrower = {};
      }

      $scope.showFormBorrower = function(ev) {
        $mdDialog.show({
          contentElement: '#addBorrower',
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose: true
        });
      };

      $scope.addBorrower = function() {
        $scope.borrower.bookLoans = [];
        libraryService.addBorrower($scope.borrower).then(function(data) {
          if(data.ssn == null){
            $scope.isDuplicateBorrower = true;
          }else{
            $scope.borrowerform.$setPristine();
            $scope.isDuplicateBorrower = false;
            $scope.borrower = {};
            $mdDialog.hide();
          }
          
        });
      }

      $scope.cancel = function() {
        if($scope.borrowerform){
          $scope.borrowerform.$setPristine();
          $scope.borrowerform.$setUntouched();
        }
        if($scope.bookForm){
          $scope.bookForm.$setPristine();
          $scope.bookForm.$setUntouched();
        }
        $mdDialog.hide();
      }

    }]);
