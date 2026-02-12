package com.hotel.book.dto;

public class CustomerResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;

    public CustomerResponseDTO() {
    }

    public CustomerResponseDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public static CustomerResponseDTOBuilder builder() {
        return new CustomerResponseDTOBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static class CustomerResponseDTOBuilder {
        private Long id;
        private String name;
        private String email;
        private String phone;

        public CustomerResponseDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerResponseDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerResponseDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerResponseDTOBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerResponseDTO build() {
            return new CustomerResponseDTO(id, name, email, phone);
        }
    }
}

