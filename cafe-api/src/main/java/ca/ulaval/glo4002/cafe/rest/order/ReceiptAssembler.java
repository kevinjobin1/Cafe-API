package ca.ulaval.glo4002.cafe.rest.order;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.receipt.Receipt;

public class ReceiptAssembler {

  public ReceiptResponse toResponse(Receipt receipt) {
    List<String> orderedProductNames = new ArrayList<>();
    for (Product product : receipt.getOrders()) {
      orderedProductNames.add(product.getName());
    }
    double subTotal = ceilNumberToTwoDecimals(receipt.getSubTotal());
    double taxes = ceilNumberToTwoDecimals(receipt.getTaxes());
    double tip = ceilNumberToTwoDecimals(receipt.getTip());
    double total = ceilNumberToTwoDecimals(receipt.getTotal());

    return new ReceiptResponse(orderedProductNames, subTotal, taxes, tip, total);
  }

  private double ceilNumberToTwoDecimals(double number) {

    // This first pass ensures we ignore numerical representation error of decimal values
    DecimalFormat decimalFormat = new DecimalFormat("#.########");
    double roundedNumber;
    try {
      roundedNumber = decimalFormat.parse(decimalFormat.format(number)).doubleValue();
    }
    catch (ParseException e) {
      throw new RuntimeException();
    }

    decimalFormat = new DecimalFormat("#.##");
    decimalFormat.setRoundingMode(RoundingMode.CEILING);
    try {
      return decimalFormat.parse(decimalFormat.format(roundedNumber)).doubleValue();
    }
    catch (ParseException e) {
      throw new RuntimeException();
    }
  }

}
