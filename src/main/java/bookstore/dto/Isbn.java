package bookstore.dto;

public class Isbn {

        private String isbn;

        // Конструктор
        public Isbn(String isbn) {
            this.isbn = isbn;
        }

        // Getters и Setters
        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
    }
