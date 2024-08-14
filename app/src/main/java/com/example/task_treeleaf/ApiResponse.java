package com.example.task_treeleaf;

public class ApiResponse<T> {
    private Status status;
    private T data;
    private String message;

    public ApiResponse(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResponse<T> loading(String message) {
        return new ApiResponse<>(Status.LOADING, null, message);
    }

    public static <T> ApiResponse<T> completed(T data) {
        return new ApiResponse<>(Status.COMPLETED, data, "");
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(Status.ERROR, null, message);
    }

    public boolean isLoading() {
        return status == Status.LOADING;
    }

    public boolean isCompleted() {
        return status == Status.COMPLETED;
    }

    public boolean isError() {
        return status == Status.ERROR;
    }

    @Override
    public String toString() {
        return "Status: " + status + "\nMessage: " + message + "\nData: " + data;
    }
}

