package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PARTICIPANT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PARTICIPANT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_ALT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_ALT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.activity.Amount.MESSAGE_CONSTRAINTS;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExpenseCommand;
import seedu.address.model.activity.Amount;


public class ExpenseCommandParserTest {
    private ExpenseCommandParser parser = new ExpenseCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ArrayList<String> persons = new ArrayList<String>() {

            {
                add(VALID_NAME_BOB);
            }
        };
        ArrayList<Amount> amounts = new ArrayList<>() {

            {
                add(new Amount(Double.parseDouble(VALID_AMOUNT)));
            }
        };

        assertParseSuccess(
            parser,
            PREAMBLE_WHITESPACE
            + PARTICIPANT_DESC_BOB
            + VALID_AMOUNT_DESC
            + VALID_EXPENSE_DESCRIPTION_DESC,
            new ExpenseCommand(
                persons,
                amounts,
                VALID_EXPENSE_DESCRIPTION
            )
        );
    }

    @Test
    public void parse_multiplePersons_success() {
        ArrayList<String> persons = new ArrayList<String>() {

            {
                add(VALID_NAME_BOB);
                add(VALID_NAME_AMY);
            }
        };
        ArrayList<Amount> amounts = new ArrayList<>() {

            {
                add(new Amount(Double.parseDouble(VALID_AMOUNT)));
                add(new Amount(Double.parseDouble(VALID_AMOUNT_ALT)));
            }
        };
        // This checks that the sequence of amounts is indeed as input
        assertParseSuccess(
            parser,
            PREAMBLE_WHITESPACE
            + PARTICIPANT_DESC_BOB
            + VALID_AMOUNT_DESC
            + PARTICIPANT_DESC_AMY
            + VALID_AMOUNT_ALT_DESC
            + VALID_EXPENSE_DESCRIPTION_DESC,
            new ExpenseCommand(
                persons,
                amounts,
                VALID_EXPENSE_DESCRIPTION
            )
        );
    }

    @Test
    public void parse_negativeAmount_fail() {
        // negative amount
        assertParseFailure(
            parser,
            PREAMBLE_WHITESPACE
            + PARTICIPANT_DESC_BOB
            + INVALID_AMOUNT_DESC,
            MESSAGE_CONSTRAINTS
        );
    }
}
