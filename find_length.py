

if __name__ == '__main__':
    total = '''

@@ -719,4 +719,48 @@ public class BasicParserFilteringTest extends BaseTest
         );
         assertEquals(a2q("[{'empty_array':[]}]"), readAndWrite(JSON_F, p));
     }
+
+    public void testExcludeObjectAtTheBeginningOfArray() throws Exception {
+        JsonParser p0 = JSON_F.createParser(a2q(
+                "{'parent':[{'exclude':false},{'include':true}]}"));
+        JsonParser p = new FilteringParserDelegate(p0,
+                new NameMatchFilter(new String[] { "include" } ),
+                Inclusion.INCLUDE_ALL_AND_PATH,
+                false // multipleMatches
+        );
+        assertEquals(a2q("{'parent':[{'include':true}]}"), readAndWrite(JSON_F, p));
+    }
+
+    public void testExcludeObjectAtTheEndOfArray() throws Exception {
+        JsonParser p0 = JSON_F.createParser(a2q(
+                "{'parent':[{'include':true},{'exclude':false}]}"));
+        JsonParser p = new FilteringParserDelegate(p0,
+                new NameMatchFilter(new String[] { "include" } ),
+                Inclusion.INCLUDE_ALL_AND_PATH,
+                false // multipleMatches
+        );
+        assertEquals(a2q("{'parent':[{'include':true}]}"), readAndWrite(JSON_F, p));
+    }
+
+    public void testExcludeObjectInMiddleOfArray() throws Exception {
+        JsonParser p0 = JSON_F.createParser(a2q(
+                "{'parent':[{'include-1':1},{'skip':0},{'include-2':2}]}"));
+        JsonParser p = new FilteringParserDelegate(p0,
+                new NameMatchFilter(new String[]{"include-1", "include-2"}),
+                Inclusion.INCLUDE_ALL_AND_PATH,
+                true // multipleMatches
+        );
+        assertEquals(a2q("{'parent':[{'include-1':1},{'include-2':2}]}"), readAndWrite(JSON_F, p));
+    }
+
+    public void testExcludeLastArrayInsideArray() throws Exception {
+        JsonParser p0 = JSON_F.createParser(a2q(
+                "['skipped', [], ['skipped']]"));
+        JsonParser p = new FilteringParserDelegate(p0,
+                INCLUDE_EMPTY_IF_NOT_FILTERED,
+                Inclusion.INCLUDE_ALL_AND_PATH,
+                true // multipleMatches
+        );
+        assertEquals(a2q("[[]]"), readAndWrite(JSON_F, p));
+    }
 }

    '''

    found = '''
public class BasicParserFilteringTest extends Base
nclude':true}]}"), readAndWrite(JSON_F, p));
+ }
+
nclude':true}]}"), readAndWrite(JSON_F, p));
+ }
+

    '''

    longest = '''
public class BasicParserFilteringTest extends Base
    '''

    total = total.replace("\n", "")
    found = found.replace("\n", "")
    longest = longest.replace("\n", "")

    total = len(total)
    found = len(found)
    longest = len(longest)

    found_total = round(found/total, 4)
    longest_total = round(longest/total, 4)
    
    print(found_total, longest_total)
    print(total)