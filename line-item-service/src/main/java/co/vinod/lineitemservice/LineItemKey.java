package co.vinod.lineitemservice;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LineItemKey implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer productId;

}
