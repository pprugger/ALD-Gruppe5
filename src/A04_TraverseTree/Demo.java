package A04_TraverseTree;

public class Demo {

    public static void main(String[] args) {

        WoerterbuchCountWordsTest test = new WoerterbuchCountWordsTest();
        WoerterbuchWordsPrefixTest test2 = new WoerterbuchWordsPrefixTest();

        try
        {
            test.setUp();
            test.noWord();
            test.singleWord();
            test.threeWords();
            test.wholeTree();

            test2.setUp();
            test2.emptyPrefix();
            test2.noMatch();
            test2.matchSingleChar();
            test2.matchMultiChar();
            test2.matchMultiChar2();
            test2.matchMultiChar3();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
