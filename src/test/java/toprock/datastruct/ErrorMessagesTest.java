package toprock.datastruct;

import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ErrorMessagesTest {

    @Test
    public void test01() {
        val errors = new ErrorMessages();
        errors.add("username", "入力必須項目です。");
        errors.add("password", "大文字が入力されていません。");
        errors.add("password", "過去に使用していたパスワードです。");
        assertThat(errors.isNG(), is(true)); //
        assertThat(errors.isOK(), is(false)); //
    }

    @Test
    public void testJoinedErrorMessage() {
        val errors = new ErrorMessages();
        errors.add("username", "入力必須項目です。");
        errors.add("password", "大文字が入力されていません。");
        errors.add("password", "過去に使用していたパスワードです。");

        assertThat(errors.isNG(), is(true)); //
        val got = errors.getJoinedMessage("password", "</br>");
        val expect = "大文字が入力されていません。</br>過去に使用していたパスワードです。";
        assertThat(got,is(expect));
    }
}