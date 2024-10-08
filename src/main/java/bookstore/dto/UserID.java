package bookstore.dto;

import java.util.List;

public class UserID {

        private String userID;
        private String username;
        private List<String> books;

        public UserID() {
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getUsername() {
            return username;
        }

    public void setUsername(String username) { // Измените параметр на username
        this.username = username; // Теперь присваиваем параметр полю
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}

