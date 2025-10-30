package com.example.clinica.dto;

public class ApiResponse<T> {
    private String status;
    private String mensagem;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(String status, String mensagem, T data) {
        this.status = status;
        this.mensagem = mensagem;
        this.data = data;
    }

    // fábricas de conveniência
    public static <T> ApiResponse<T> sucesso(String mensagem, T data) {
        return new ApiResponse<>("sucesso", mensagem, data);
    }

    public static <T> ApiResponse<T> sucesso(String mensagem) {
        return new ApiResponse<>("sucesso", mensagem, null);
    }

    public static <T> ApiResponse<T> erro(String mensagem) {
        return new ApiResponse<>("erro", mensagem, null);
    }

    // getters / setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
