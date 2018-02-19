package edu.cnm.deepdive;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Parser {

  private Options options;

  public Parser() {
    options = new Options();
    Option option = Option.builder("l")
                          .longOpt("length")
                          .hasArg(true)
                          .argName("words")
                          .numberOfArgs(1)
                          .optionalArg(false)
                          .required(false)
                          .type(Number.class)
                          .desc("number of words in passphrase")
                          .build();
    options.addOption(option);
    option = Option.builder("?")
                   .longOpt("help")
                   .hasArg(false)
                   .required(false)
                   .desc("display usage")
                   .build();
    options.addOption(option);
    option = Option.builder("c")
                   .longOpt("command")
                   .hasArg(true)
                   .argName("operation")
                   .numberOfArgs(1)
                   .optionalArg(false)
                   .required(false)
                   .type(String.class)
                   .desc("operation to perform")
                   .build();
    options.addOption(option);
    option = Option.builder("f")
        .longOpt("for")
        .hasArg(true)
        .argName("identifier")
        .numberOfArgs(1)
        .optionalArg(false)
        .required(false)
        .type(String.class)
        .desc("unique identifier")
        .build();
    options.addOption(option);
    option = Option.builder("u")
        .longOpt("user")
        .hasArg(true)
        .argName("username or id")
        .numberOfArgs(1)
        .optionalArg(false)
        .required(false)
        .type(String.class)
        .desc("username or id of credentials")
        .build();
    options.addOption(option);
    option = Option.builder("p")
        .longOpt("pass")
        .hasArg(true)
        .argName("password/passphrase")
        .numberOfArgs(1)
        .optionalArg(false)
        .required(false)
        .type(String.class)
        .desc("password/passphrase for credentials")
        .build();
    options.addOption(option);
    option = Option.builder("g")
        .longOpt("generate")
        .hasArg(false)
        .required(false)
        .desc("generate random password/passphrase")
        .build();
    options.addOption(option);
    option = Option.builder("i")
        .longOpt("info")
        .hasArg(true)
        .argName("additional information")
        .numberOfArgs(1)
        .optionalArg(false)
        .required(false)
        .type(String.class)
        .desc("freeform information (if spaces included entire "
            + "value must be surrounded in double quotes)")
        .build();
    options.addOption(option);
    option = Option.builder("t")
        .longOpt("tags")
        .hasArgs()
        .argName("category tags")
        //.optionalArg(true)
        .numberOfArgs(Option.UNLIMITED_VALUES)
        .required(false)
        .type(String.class)
        .desc("space-delimited list of categories")
        .build();
    options.addOption(option);
  }

  public Map<String, Object> parse(String[] args) throws ParseException {
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);
    if (cmd.hasOption("?")) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("java edu.cnm.deepdive.Main [options]", options);
      return null;
    }
    HashMap<String, Object> optionsSpecified = new HashMap<>();
    for (Option option : cmd.getOptions()) {
      Object value = cmd.getParsedOptionValue(option.getOpt());
      optionsSpecified.put(option.getOpt(), value);
    }
    validate(optionsSpecified);
    return optionsSpecified;
  }

  private void validate(Map<String, Object> options) {
    // TODO 
  }


  public static class InvalidOptionCombination extends ParseException{

    public InvalidOptionCombination(String message) {
      super(message);
    }
  }

}
