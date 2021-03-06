// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.servlet.filter;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

public class ByteArrayResponseWrapperTest extends TestCase {

	public void testWrite() throws IOException {
		HttpServletResponseWrapper rw = mock(HttpServletResponseWrapper.class);
		ServletOutputStream os = mock(ServletOutputStream.class);

		when(rw.getOutputStream()).thenReturn(os);

		ByteArrayResponseWrapper wrappedResponse = new ByteArrayResponseWrapper(rw);
		ServletOutputStream sos = wrappedResponse.getOutputStream();

		sos.print(173);
		sos.print("WOW");

		byte[] bytes = wrappedResponse.toByteArray();
		assertNotNull(bytes);
		assertEquals(6, bytes.length);
		assertEquals('1', bytes[0]);
		assertEquals('W', bytes[3]);

		PrintWriter pw = wrappedResponse.getWriter();
		pw.write("YYZ");
		pw.flush();

		bytes = wrappedResponse.toByteArray();
		assertEquals(9, bytes.length);
		assertEquals('1', bytes[0]);
		assertEquals('W', bytes[3]);
		assertEquals('Y', bytes[6]);
	}
}
