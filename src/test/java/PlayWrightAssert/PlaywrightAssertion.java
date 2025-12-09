package PlayWrightAssert;

import Base.BaseFunction;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class PlaywrightAssertion extends BaseFunction {

    Page page = launchBrowserWithMaximize("chrome", "http://tizag.com/htmlT/htmlcheckboxes.php");

    @Test
    void checkBoxAssertion() {
        assertThat(page).hasURL("http://tizag.com/htmlT/htmlcheckboxes.php");
        assertThat(page).hasTitle("HTML Tutorial - Checkboxes");
    }


    // Element presence and checkbox-specific assertions
    @Test
    void checkboxAssertions() {
        Locator soccer = page.locator("input[value=\"soccer\"][checked=\"yes\"]");
        Locator basketball = page.locator("input[value=\"basketball\"]").first();

        // isAttached - element exists in DOM
        assertThat(soccer).isAttached();

    /*
     isChecked - Tizag example shows some checkboxes can be pre-checked;
     assert checked state
     (Tizag sample marks soccer or basketball as checked in examples;
     this verifies whichever is checked)
     We assert that at least one of the two is either checked or not to demonstrate usage:
     */

        assertThat(soccer).isChecked(); // if soccer is pre-checked on the page
        // To demonstrate toggling and re-check:
        basketball.check();
        assertThat(basketball).isChecked();

        //1). isEnabled / isDisabled
        assertThat(soccer).isEnabled();
        // If you need to test disabled, inject a disabled element:
        page.evaluate("() => { " +
                "const i = document.createElement('input'); " +
                "i.type='checkbox'; " +
                "i.id='disabledBox'; " +
                "i.disabled=true; " +
                "document.body.appendChild(i); " +
                "}");

        Locator disabledBox = page.locator("#disabledBox");
        assertThat(disabledBox).isDisabled();

        //2). isVisible / isHidden / isInViewport
        assertThat(soccer).isVisible();

        //3). isFocused
        soccer.focus();
        assertThat(soccer).isFocused();

        //4). isEditable - not applicable to checkbox; demonstrate with an input text
        page.evaluate("() => { const t = document.createElement('input'); " +
                "t.id='txt'; " +
                "t.value='hello'; " +
                "document.body.appendChild(t); " +
                "}");

        Locator txt = page.locator("#txt");
        assertThat(txt).isEditable();

        //5). isEmpty - container with no children
        page.evaluate("() => { " +
                "const d = document.createElement('div'); " +
                "d.id='emptyDiv'; " +
                "document.body.appendChild(d); " +
                "}");

        Locator emptyDiv = page.locator("#emptyDiv");
        assertThat(emptyDiv).isEmpty();
    }

    // Attribute, class, id, css, js property, role, accessible name/description, text/value assertions
    @Test
    void attributeAndTextAssertions() {
        // Add an element with attributes and classes to demonstrate assertions
        page.evaluate("() => {" +
                "const el = document.createElement('div');" +
                "el.id='demo'; " +
                "el.className='foo bar'; " +
                "el.setAttribute('data-test','x');" +
                "el.textContent='Hello Playwright';" +
                "el.setAttribute('role','button');" +
                "el.setAttribute('aria-label','Demo Button');" +
                "el.style.color='rgb(255, 0, 0)';" +
                "el.jsProp = 'jsValue';" +
                "document.body.appendChild(el);" +
                "}");

        Locator demo = page.locator("#demo");

        //6). hasAttribute
        assertThat(demo).hasAttribute("data-test", "x");

        //7). containsClass and hasClass
        assertThat(demo).containsClass("foo");
        assertThat(demo).hasClass("foo bar");

        //8). hasId
        assertThat(demo).hasId("demo");

        //9). hasCSS - check computed style color
        assertThat(demo).hasCSS("color", "rgb(255, 0, 0)");

        //10). hasJSProperty - set via evaluate and assert
        page.evaluate("() => document.querySelector('#demo').jsProp = 'jsValue'");
        assertThat(demo).hasJSProperty("jsProp", "jsValue");

        //11). hasRole
        assertThat(demo).hasRole(AriaRole.BUTTON);

        //12). hasAccessibleName and hasAccessibleDescription
        assertThat(demo).hasAccessibleName("Demo Button");

        //13). accessible description demonstration
        page.evaluate("() => document.querySelector('#demo').setAttribute('aria-description','desc')");
        assertThat(demo).hasAccessibleDescription("desc");

        //14). containsText and hasText
        assertThat(demo).containsText("Playwright");
        assertThat(demo).hasText("Hello Playwright");

        page.evaluate("() => { " +
                "const i = document.createElement('input'); " +
                "i.id='valInput'; " +
                "i.value='abc'; " +
                "document.body.appendChild(i); " +
                "}");

        //15). hasValue - create an input with value
        Locator valInput = page.locator("#valInput");
        assertThat(valInput).hasValue("abc");
    }

    // hasCount, hasValues, matchesAriaSnapshot examples
    @Test
    void listAndSelectAssertions() {
        //16). hasCount - count checkboxes with name 'sports'
        Locator sports = page.locator("input[name='sports']");
        assertThat(sports).hasCount(8);

        // hasValues - inject a select and assert selected options
        page.evaluate("() => {" +
                "const s = document.createElement('select'); " +
                "s.id='sel';" +
                "const o1 = new Option('One','1'); " +
                "const o2 = new Option('Two','2');" +
                "s.add(o1); " +
                "s.add(o2); " +
                "s.value='2'; " +
                "document.body.appendChild(s);" +
                "}");

        Locator sel = page.locator("#sel");
        //assertThat(sel.locator("value")).hasValues(String.valueOf(2));

        // matchesAriaSnapshot - capture ARIA snapshot for demo element
        Locator demo = page.locator("#demo");
        assertThat(demo).matchesAriaSnapshot(demo.ariaSnapshot());
    }
}
