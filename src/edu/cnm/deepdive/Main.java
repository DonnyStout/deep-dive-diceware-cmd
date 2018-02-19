package edu.cnm.deepdive;

import edu.cnm.deepdive.security.Diceware;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.cli.ParseException;

public class Main {

  public static void main(String[] args) throws NoSuchAlgorithmException, ParseException {
    ResourceBundle bundle = ResourceBundle.getBundle("wordlist");
    Diceware dw = new Diceware(bundle);
    Parser parser = new Parser();
    Map<String, Object> options = parser.parse(args);
    if (options != null) {
      int length = ((Number) options.getOrDefault("l", 6)).intValue();
      System.out.println(dw.generate(length, " "));
    }
  }

}
