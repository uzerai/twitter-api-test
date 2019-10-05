package xyz.outofmysystem.h1.error;

import lombok.Builder;
import lombok.Data;

/**
 * Generic class for error return body
 */
@Data
@Builder
public class ErrorBody {

  private int code;
  private String message;
  private String details;

}