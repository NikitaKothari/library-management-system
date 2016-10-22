app.factory('libraryService', [
    '$http',
    function($http) {
      return {
        getBooks: function(searchText) {
          var promise = $http.get(
                  'http://localhost:8080/library/book/getByTitle/' + searchText)
                  .then(function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },
        
        getBooks: function(searchText) {
          var promise = $http.get(
                  'http://localhost:8080/library/book/getByTitle/' + searchText)
                  .then(function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },
        
        getBooksByKey: function(key) {
          var promise = $http.get(
                  'http://localhost:8080/library/book/getBooksByKey/' + key)
                  .then(function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },
        
        getBooksByAuthorName: function(authorName) {
          var promise = $http.get(
                  'http://localhost:8080/library/book/getBooksByAuthorName/' + authorName)
                  .then(function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },

        getBook: function(isbn) {
          var promise = $http.get(
                  'http://localhost:8080/library/book/getByIsbn/' + isbn).then(
                  function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },
        
        addBook: function(book) {
          var promise = $http.post(
                  'http://localhost:8080/library/book', book, "").then(
                  function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },
        
        updateBook: function(book) {
          var promise = $http.put(
                  'http://localhost:8080/library/book', book, "").then(
                  function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },

        getByCardId: function(cardId) {
          var promise = $http.get(
                  'http://localhost:8080/library/borrower/getByCardId/' + cardId)
                  .then(function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },

        addBorrower: function(borrower) {
          var promise = $http.post('http://localhost:8080/library/borrower', borrower, "").then(
                  function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },

        updateBorrower: function(borrower) {
          var promise = $http.put('http://localhost:8080/library/borrower', borrower, "").then(
                  function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },
        
        addBookLoans: function(bookLoans) {
          var promise = $http.post('http://localhost:8080/library/bookLoans', bookLoans, "").then(
                  function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },
        
        updateBookLoans: function(loanId) {
          var promise = $http.put('http://localhost:8080/library/bookLoans/' + loanId, "", "").then(
                  function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        },
        
        payDue: function(cardId) {
          var promise = $http.put('http://localhost:8080/library/payDue/' + cardId, "", "").then(
                  function(response) {
                    return response.data;
                  }, function(error) {
                  })
          return promise;
        }
        
        
      }
    }]);