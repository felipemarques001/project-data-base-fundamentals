package com.FelipeMarques.salesController.Exceptions.ExceptionResponse;

import java.util.Date;

public record ErrorDetails(Date date,
                           String message,
                           String details) {
}
