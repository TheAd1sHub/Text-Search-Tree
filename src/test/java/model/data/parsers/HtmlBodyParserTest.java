package model.data.parsers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class HtmlBodyParserTest {

    private static HtmlBodyParser parser;

    @BeforeAll
    public static void init() {
        parser = new HtmlBodyParser();
    }


    @Test
    @DisplayName("Html page contents parsing")
    public void testHtmlPageContentsParsing() {
        final String html = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
   <h1>Test text</h1>
   <p>Hello world!</p>
</body>
</html>
""";

        final String expectedParsingResult = "Test text\nHello world!";

        final String parsingResult = parser.getContents(html);

        assertEquals(expectedParsingResult, parsingResult);
    }

    @Test
    @DisplayName("Html page contents parsing with interactive elements")
    public void testHtmlPageContentsParsing2() {
        final String html = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div class="code">
        <h1>Header text</h1>
        <p>Paragraph text</p>
        <button class="interactive">Button text</button>
    </div>
</body>
</html>
""";

        final String expectedParsingResult = "Header text\nParagraph text\nButton text";

        final String parsingResult = parser.getContents(html);

        assertEquals(expectedParsingResult, parsingResult);
    }

    @Test
    @DisplayName("Full-scope html page contents parsing")
    public void testHtmlPageContentsParsing3() {

        final String html = """
<html><head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dan Bornstein</title>
</head>
                       
<body background="gbi.gif">
<img hspace="20" vspace="5" align="left" src="fuzzicon.jpg" alt="fuzzicon">
<h1><a href="danfuzz-1.jpg">Dan</a>
<a href="danfuzz-2.jpg">Bornstein</a></h1>
<p>(a.k.a. <i>Danfuzz</i>, for <a href="mysterious.html">mysterious</a> reasons)</p>
<p><br clear="all"></p>

<ul>
  <li><a href="../../">A bunch of fun stuff cleverly disguised as a web service</a></li>
  <li><a href="contact.html">Contact me</a>
    / <a href="https://github.com/danfuzz">GitHub</a>
    / <a rel="me" href="https://mastodon.social/@danfuzz">Mastodon</a>
    / <a href="https://stackoverflow.com/users/387103/danfuzz">StackOverflow</a>
  </li>
  <li>Random bits of prose / slice o' my life
  <ul>
    <li><a href="artsy/">Words And Pictures</a></li>
    <li><a href="pics/">The Good, The Bad, and The Danfuzz.</a> Am I a narcissist? Perhaps. But if you follow the link, then you'll only be encouraging me.</li>
  </ul>
  </li>
</ul>

<hr>

<h2 align="center">My Book About Me</h2>

<p align="center"><i>(Remember, it's not a home page unless you have something extremely self-centered on it!)</i></p>

<p>I started my career as one of the original employees at Kaleida Labs (may she R.I.P.), makers of ScriptX (a pretty cool multimedia programming system, which later morphed into a net-centric Java thingy before finally dying). More recently, I was at Electric Communities (aka Communities.com, may she R.I.P.) for about two years, working on their Java extensions known as <a href="https://www.erights.org/"><i>E</i></a>.
In 1998, I cofounded Xigo with my brother Jeremy and my good friend Lenny. It was pretty fun and the business was a novel net-available service and web-hosted application.
Unfortunately, despite very promising business opportunities, the company was unable to become self-sustaining (may she R.I.P.). I eventually landed at Danger (later acquired by Microsoft, who shut the company down, may she R.I.P.), where I worked on the Danger smartphone OS. From 2005 to 2011 I was at <a href="https://www.google.com/">Google</a>, working on the <a href="https://source.android.com/">Android</a> project, where I did some of the most personally compelling work of my career to date. For my more recent doings, see my <a href="resume/">résumé</a>.</p>

<p>-dan</p>

<p><font size="-1">P.S.: I have no actual ties to the dairy industry.
<a href="/experiments/">Really.</a></font></p>


</body></html>
""";

        final String expectedParsingResult = """
Dan Bornstein
(a.k.a. Danfuzz, for mysterious reasons)
A bunch of fun stuff cleverly disguised as a web service
Contact me / GitHub / Mastodon / StackOverflow
Random bits of prose / slice o' my life

Words And Pictures
The Good, The Bad, and The Danfuzz. Am I a narcissist? Perhaps. But if you follow the link, then you'll only be encouraging me.

My Book About Me
(Remember, it's not a home page unless you have something extremely self-centered on it!)
I started my career as one of the original employees at Kaleida Labs (may she R.I.P.), makers of ScriptX (a pretty cool multimedia programming system, which later morphed into a net-centric Java thingy before finally dying). More recently, I was at Electric Communities (aka Communities.com, may she R.I.P.) for about two years, working on their Java extensions known as E. In 1998, I cofounded Xigo with my brother Jeremy and my good friend Lenny. It was pretty fun and the business was a novel net-available service and web-hosted application. Unfortunately, despite very promising business opportunities, the company was unable to become self-sustaining (may she R.I.P.). I eventually landed at Danger (later acquired by Microsoft, who shut the company down, may she R.I.P.), where I worked on the Danger smartphone OS. From 2005 to 2011 I was at Google, working on the Android project, where I did some of the most personally compelling work of my career to date. For my more recent doings, see my résumé.
-dan
P.S.: I have no actual ties to the dairy industry. Really.""";

        final String parsingResult = parser.getContents(html);

        assertEquals(expectedParsingResult, parsingResult);
    }
}
