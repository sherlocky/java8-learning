package com.sherlocky.learning.java8;

import com.alibaba.fastjson.JSON;

import strman.Strman;

public class StrmanDemo {

	public void strman() {
		println("--- append(\"f\", \"o\", \"o\", \"b\", \"a\", \"r\")");
		println(Strman.append("f", "o", "o", "b", "a", "r"));
		// result => "foobar"
		println("--- appendArray(\"f\", new String[]{\"o\", \"o\", \"b\", \"a\", \"r\"}");
		println(Strman.appendArray("f", new String[] { "o", "o", "b", "a", "r" }));
		// result => "foobar"
		println("--- between(\"[abc][def]\", \"[\", \"]\")");
		println(Strman.between("[abc][def]", "[", "]"));
		// result => ["abc","def"]
		println("--- chars(\"title\")");
		println(Strman.chars("title"));
		// result => ["t", "i", "t", "l", "e"]
		println("--- collapseWhitespace(\"foo    bar\")");
		println(Strman.collapseWhitespace("foo    bar"));
		// result => "foo bar"
		println("--- contains(\"foo bar\",\"Foo\")");
		println(Strman.contains("foo bar", "Foo"));
		// result => true
		println("--- contains(\"foo bar\",\"Foo\", true)");
		println(Strman.contains("foo bar", "Foo", true));
		// result => false
		println("--- containsAll(\"foo bar\", new String[] { \"Foo\", \"bar\" })");
		println(Strman.containsAll("foo bar", new String[] { "Foo", "bar" }));
		// result => false
		println("--- containsAll(\"foo bar\", new String[] { \"Foo\", \"bar\" }, true)");
		println(Strman.containsAll("foo bar", new String[] { "Foo", "bar" }, false));
		// result => true
		println("--- containsAny(\"bar foo\", new String[]{\"FOO\", \"BAR\", \"Test\"}, true)");
		println(Strman.containsAny("bar foo", new String[]{"FOO", "BAR", "Test"}, true));
		// result => false
		println("--- countSubstr(\"aaaAAAaaa\", \"aaa\", false, false)");
		println(Strman.countSubstr("aaaAAAaaa", "aaa", false, false));
		// result => 3
		println("--- endsWith(\"foo bar 1233\", \"BAR\", 7, false)");
		// result => true
		println(Strman.endsWith("foo bar 1233", "BAR", 7, false));
		println("--- ensureLeft(\"foobar\", \"FOO\", false)");
		println(Strman.ensureLeft("foobar", "FOO", false));
		println("--- ensureLeft(\"foobar\", \"FOO\", true)");
		println(Strman.ensureLeft("foobar", "FOO", true));
		println("--- ensureLeft(\"bar\", \"FOO\", true)");
		println(Strman.ensureLeft("bar", "FOO", true));
		// result => "foobar"
		println("--- decEncode/decDecode()...");
		println("--- binEncode/binDecode()...");
		println("--- base64Encode/base64Decode()...");
		println("--- hexEncode/hexDecode()...");
		println("--- htmlEncode/htmlDecode()...");
		println("--- inequal()...");
		println("--- head()...");
		println("--- tail()...");
		println("--- first()...");
		println("--- last()...");
		
		println("Strman.insert(\"fbar\", \"oo\", 1)");
		println(Strman.insert("fbar", "oo", 1));
		println("Strman.leftTrim(\"   foobar\")");
		println(Strman.leftTrim("   foobar"));
		println("Strman.leftPad(\"5\", \"0\", 5)");
		println(Strman.leftPad("5", "0", 5));
		println("Strman.prepend(\"ar\", \"f\", \"o\", \"o\", \"b\")");
		println(Strman.prepend("ar", "f", "o", "o", "b"));
		println("Strman.removeEmptyStrings(new String[] { \"1\", \" \", \"\", null, \"2\", \"3\" })");
		println(Strman.removeEmptyStrings(new String[] { "1", " ", "", null, "2", "3" }));
		println("Strman.removeLeft(\"foobar\", \"foo\")");
		println(Strman.removeLeft("foobar", "foo"));
		println("Strman.removeNonWords(\"*!foo&#bar()\")");
		println(Strman.removeNonWords("*!foo&#bar()"));
		println("Strman.removeSpaces(\" foo bar \")");
		println(Strman.removeSpaces(" foo bar "));
		println("Strman.repeat(\"5\", 5)");
		println(Strman.repeat("5", 5));
		println("Strman.reverse(\"kcuf\")");
		println(Strman.reverse("kcuf"));
		println("Strman.truncate(\"A Javascript string manipulation library.\", 14, \"...\")");
		println(Strman.truncate("A Javascript string manipulation library.", 14, "..."));
		println("Strman.safeTruncate(\"A Javascript string manipulation library.\", 16, \"...\")");
		println(Strman.safeTruncate("A Javascript string manipulation library.", 16, "..."));
		println("Strman.safeTruncate(\"A Javascript string manipulation library.\", 23, \"...\")");
		println(Strman.safeTruncate("A Javascript string manipulation library.", 23, "..."));
		println("Strman.shuffle(\"fuck\")");
		println(Strman.shuffle("fuck"));
		println("Strman.slugify(\"foo bar.test\")");
		println(Strman.slugify("foo bar.test"));
		println("Strman.transliterate(\"fóõ bár\")");
		println(Strman.transliterate("fóõ bár"));
		println("Strman.surround(\"div\", \"<\", \">\")");
		println(Strman.surround("div", "<", ">"));
		
		println("Strman.toCamelCase(\"makeLove\")");
		println(Strman.toCamelCase("makeLove"));
		println("Strman.toCamelCase(\"MakeLove\")");
		println(Strman.toCamelCase("MakeLove"));
		println("Strman.toCamelCase(\"make love\")");
		println(Strman.toCamelCase("make love"));
		println("Strman.toCamelCase(\"make-love\")");
		println(Strman.toCamelCase("make-love"));
		println("Strman.toCamelCase(\"make_love\")");
		println(Strman.toCamelCase("make_love"));
		println("Strman.toStudlyCase(\"makeLove\")");
		println(Strman.toStudlyCase("makeLove"));
		println("Strman.toStudlyCase(\"MakeLove\")");
		println(Strman.toStudlyCase("MakeLove"));
		println("Strman.toStudlyCase(\"make love\")");
		println(Strman.toStudlyCase("make love"));
		println("Strman.toStudlyCase(\"make-love\")");
		println(Strman.toStudlyCase("make-love"));
		println("Strman.toStudlyCase(\"make_love\")");
		println(Strman.toStudlyCase("make_love"));
		println("Strman.toDecamelize(\"makeLove\", null)");
		println(Strman.toDecamelize("makeLove", null));
		println("Strman.toDecamelize(\"MakeLove\", null)");
		println(Strman.toDecamelize("MakeLove", null));
		println("Strman.toDecamelize(\"make love\", null)");
		println(Strman.toDecamelize("make love", null));
		println("Strman.toDecamelize(\"make-love\", null)");
		println(Strman.toDecamelize("make-love", null));
		println("Strman.toDecamelize(\"make_love\", null)");
		println(Strman.toDecamelize("make_love", null));
		println("Strman.toKebabCase(\"makeLove\")");
		println(Strman.toKebabCase("makeLove"));
		println("Strman.toKebabCase(\"MakeLove\")");
		println(Strman.toKebabCase("MakeLove"));
		println("Strman.toKebabCase(\"make love\")");
		println(Strman.toKebabCase("make love"));
		println("Strman.toKebabCase(\"make-love\")");
		println(Strman.toKebabCase("make-love"));
		println("Strman.toKebabCase(\"make_love\")");
		println(Strman.toKebabCase("make_love"));
		println("Strman.toSnakeCase(\"makeLove\")");
		println(Strman.toSnakeCase("makeLove"));
		println("Strman.toSnakeCase(\"MakeLove\")");
		println(Strman.toSnakeCase("MakeLove"));
		println("Strman.toSnakeCase(\"make love\")");
		println(Strman.toSnakeCase("make love"));
		println("Strman.toSnakeCase(\"make-love\")");
		println(Strman.toSnakeCase("make-love"));
		println("Strman.toSnakeCase(\"make_love\")");
		println(Strman.toSnakeCase("make_love"));
	}

	private void println(Object obj) {
		if (obj instanceof String) {
			System.out.println(obj);
			
			return;
		}
		System.out.println(JSON.toJSONString(obj));
	}

	/**
	 * * @param args
	 * 
	 * @author zhangcx
	 * @date 2016年7月16日
	 * @since
	 */
	public static void main(String[] args) {
		StrmanDemo sd = new StrmanDemo();
		sd.strman();
	}
}