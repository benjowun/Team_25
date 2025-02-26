package at.sw21_tug.team_25.expirydates

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import at.sw21_tug.team_25.expirydates.ui.add.AddViewModel
import at.sw21_tug.team_25.expirydates.ui.errorhandling.ErrorCode
import junit.framework.TestCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("at.sw21_tug.team_25.expirydates", appContext.packageName)
    }
}

@RunWith(AndroidJUnit4::class)
class EXPDAT_002_test_01  : TestCase() {
    private lateinit var viewModel: AddViewModel
    private var today: Long = Calendar.getInstance().timeInMillis

    @Before
    public override fun setUp() {
        //val context = ApplicationProvider.getApplicationContext<Context>()
        viewModel = AddViewModel()
    }

    @Test
    fun testBasic() {
        val success = viewModel.saveValues("test123", today)
        assertEquals(success, ErrorCode.OK)
        assertEquals(viewModel.text, "test123")
        assertEquals(viewModel.date, today)
    }

    @Test
    fun testEmpty() {
        val success = viewModel.saveValues("", today)
        assertEquals(success, ErrorCode.INPUT_ERROR)
        assertEquals(viewModel.text, "")
        assertEquals(viewModel.date, 0)
    }

    @Test
    fun testMax() {
        val success = viewModel.saveValues("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", today)
        assertEquals(success, ErrorCode.INPUT_ERROR)
        assertEquals(viewModel.text, "")
        assertEquals(viewModel.date, 0)
    }

    @Test
    fun testDateFuture() {
        val success = viewModel.saveValues("test", today - 106400000 )
        assertEquals(success, ErrorCode.DATE_ERROR)
        assertEquals(viewModel.text, "")
        assertEquals(viewModel.date, 0)
    }

    @Test
    fun testDateNegative() {
        val success = viewModel.saveValues("test", -1)
        assertEquals(success, ErrorCode.DATE_ERROR)
        assertEquals(viewModel.text, "")
        assertEquals(viewModel.date, 0)
    }
}
