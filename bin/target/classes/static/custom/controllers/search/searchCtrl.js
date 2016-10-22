app.controller('searchCtrl', [
    '$scope',
    'libraryService',
    '$mdDialog',
    "$location",
    "NgTableParams",
    function($scope, libraryService, $mdDialog, $location, NgTableParams) {
      $scope.isLoading = true;
      //$scope.searchText = $location.search().text;
      $scope.books = [];
      var self = this;

      $scope.key = $location.search().key;
      $scope.autherName = $location.search().autherName;
      $scope.title = $location.search().title;
      if ($scope.key && $scope.key.length > 0) {
        libraryService.getBooksByKey($scope.key).then(function(data) {
          $scope.books = data;
          $scope.booksTable = new NgTableParams({
            // initial sort order
            sorting: {
              title: "asc"
            },
            count: 3
          }, {
            dataset: $scope.books,
            counts: []
          });
          $scope.isLoading = false;

        });
      } else if ($scope.title && $scope.title.length > 0) {
        libraryService.getBooks($scope.title).then(function(data) {
          $scope.books = data;
          $scope.booksTable = new NgTableParams({
            // initial sort order
            sorting: {
              title: "asc"
            },
            count: 3
          }, {
            dataset: $scope.books,
            counts: []
          });
          $scope.isLoading = false;

        });
      } else if ($scope.autherName && $scope.autherName.length > 0) {
        libraryService.getBooksByAuthorName($scope.autherName).then(
                function(data) {
                  $scope.books = data;
                  $scope.booksTable = new NgTableParams({
                    // initial sort order
                    sorting: {
                      title: "asc"
                    },
                    count: 3
                  }, {
                    dataset: $scope.books,
                    counts: []
                  });
                  $scope.isLoading = false;

                });
      }

      /*libraryService.getBooks($scope.searchText).then(function(data) {
        $scope.books = data;
        console.info($scope.books);
        $scope.booksTable = new NgTableParams({
          // initial sort order
          sorting: {
            title: "asc"
          },
          count: 5
        }, {
          dataset: $scope.books
        });
        $scope.isLoading = false;
      });*/

    }])