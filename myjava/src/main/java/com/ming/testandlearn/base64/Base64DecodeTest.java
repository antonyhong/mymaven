package com.ming.testandlearn.base64;

import java.util.Base64;

/**
 * Created by hongyongming on 2015/12/23.
 */
public class Base64DecodeTest {

    public static void main(String[] args) {
        //Base64.getDecoder().decode(getEncodeContentWithEnter());
        Base64.getDecoder().decode(getEncodeContent());
    }

    private static String getEncodeContent(){
        return "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4KPHJlcXVlc3Q+CiAgPGl0ZW1z" +
                "PgogICAgPGl0ZW0+CiAgICAgIDxpdGVtTm8+QlRC5ZWG5ZOB57yW56CBPC9pdGVtTm8+CiAgICAg" +
                "IDxuYW1lRW4+QlRC5ZWG5ZOB6Iux5paH5o+P6L+wPC9uYW1lRW4+CiAgICAgIDxuYW1lQ24+QlRC" +
                "5ZWG5ZOB5Lit5paH5o+P6L+wPC9uYW1lQ24+CiAgICAgIDxiYXJDb2RlPuWVhuWTgeadoeW9oueg" +
                "gTwvYmFyQ29kZT4KICAgICAgPGpvaW5UaW1lPuWVhuWTgeWKoOWFpeaXtumXtDwvam9pblRpbWU+" +
                "CiAgICAgIDxpdGVtQ2xhc3M+SVRFTeeahOWIhuexuzwvaXRlbUNsYXNzPgogICAgICA8Y2F0ZWdv" +
                "cnk+5ZWG5ZOB5Li75YiG57G7PC9jYXRlZ29yeT4KICAgICAgPHN1YkNhdGVnb3J5PuWVhuWTgeWt" +
                "kOWIhuexuzwvc3ViQ2F0ZWdvcnk+CiAgICAgIDx3ZWJDYXRlPue9keermeS4u+WIhuexuzwvd2Vi" +
                "Q2F0ZT4KICAgICAgPHdlYlN1YkNhdGU+572R56uZ5a2Q5YiG57G7PC93ZWJTdWJDYXRlPgogICAg" +
                "ICA8YnJhbmROYW1lPuWVhuWTgeWTgeeJjOWQjeensDwvYnJhbmROYW1lPgogICAgICA8dmVuZG9y" +
                "TmFtZT7kvpvlupTllYblkI3np7A8L3ZlbmRvck5hbWU+CiAgICAgIDx2ZW5kb3JDb2RlPuS+m+W6" +
                "lOWVhuWvueW6lOWVhuWTgeS7o+eggTwvdmVuZG9yQ29kZT4KICAgICAgPGNvbnNpZ25tZW50PuaY" +
                "r+WQpuWvhOWUrigwOuWQpu+8mzE65pivKTwvY29uc2lnbm1lbnQ+CiAgICAgIDxkaXNjb250aW51" +
                "ZWQ+5piv5ZCm5pat6LSnKDA65ZCm77ybMTrmmK8pPC9kaXNjb250aW51ZWQ+CiAgICAgIDxjb2xv" +
                "cj7popzoibI8L2NvbG9yPgogICAgICA8c3BlY2lmaWNhdGlvbj7llYblk4HmnaHnoIHop4TmoLw8" +
                "L3NwZWNpZmljYXRpb24+CiAgICAgIDx1bml0UHJpY2U+5ZWG5ZOB5Y2V5Lu3PC91bml0UHJpY2U+" +
                "CiAgICAgIDxzdGFuZGFyZENvc3Q+5qCH5YeG5oiQ5pysPC9zdGFuZGFyZENvc3Q+CiAgICAgIDxh" +
                "dmVyYWdlQ29zdD7lubPlnYfmiJDmnKw8L2F2ZXJhZ2VDb3N0PgogICAgICA8ZGlzY291bnQ+5oqY" +
                "5omjPC9kaXNjb3VudD4KICAgICAgPGxwUHJpY2U+5pyA5ZCO6YeH6LSt5Lu3PC9scFByaWNlPgog" +
                "ICAgICA8cGFyZW50Q29kZT7niLbnuqdJVEVN57yW56CBPC9wYXJlbnRDb2RlPgogICAgICA8dW5p" +
                "dD7orqHph4/ljZXkvY08L3VuaXQ+CiAgICAgIDxkZXNjcmlwdGlvbj5JVEVN5o+P6L+wPC9kZXNj" +
                "cmlwdGlvbj4KICAgICAgPGN1cnJlbmN5PuWkluaxhyDlpoLvvJpHQlA8L2N1cnJlbmN5PgogICAg" +
                "ICA8Zm9yZWlnbkNvc3Q+5aSW5rGH6YeR6aKdPC9mb3JlaWduQ29zdD4KICAgICAgPGFjdGl2ZT7l" +
                "lYblk4HnirbmgIHvvIgwOuaXoOaViO+8mzE65pyJ5pWI77yJPC9hY3RpdmU+CiAgICA8L2l0ZW0+" +
                "CiAgPC9pdGVtcz4KPC9yZXF1ZXN0Pgo=";
    }

    private static String getEncodeContentWithEnter(){
        return "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4KPHJlcXVlc3Q+CiAgPGl0ZW1z\n" +
                "PgogICAgPGl0ZW0+CiAgICAgIDxpdGVtTm8+QlRC5ZWG5ZOB57yW56CBPC9pdGVtTm8+CiAgICAg\n" +
                "IDxuYW1lRW4+QlRC5ZWG5ZOB6Iux5paH5o+P6L+wPC9uYW1lRW4+CiAgICAgIDxuYW1lQ24+QlRC\n" +
                "5ZWG5ZOB5Lit5paH5o+P6L+wPC9uYW1lQ24+CiAgICAgIDxiYXJDb2RlPuWVhuWTgeadoeW9oueg\n" +
                "gTwvYmFyQ29kZT4KICAgICAgPGpvaW5UaW1lPuWVhuWTgeWKoOWFpeaXtumXtDwvam9pblRpbWU+\n" +
                "CiAgICAgIDxpdGVtQ2xhc3M+SVRFTeeahOWIhuexuzwvaXRlbUNsYXNzPgogICAgICA8Y2F0ZWdv\n" +
                "cnk+5ZWG5ZOB5Li75YiG57G7PC9jYXRlZ29yeT4KICAgICAgPHN1YkNhdGVnb3J5PuWVhuWTgeWt\n" +
                "kOWIhuexuzwvc3ViQ2F0ZWdvcnk+CiAgICAgIDx3ZWJDYXRlPue9keermeS4u+WIhuexuzwvd2Vi\n" +
                "Q2F0ZT4KICAgICAgPHdlYlN1YkNhdGU+572R56uZ5a2Q5YiG57G7PC93ZWJTdWJDYXRlPgogICAg\n" +
                "ICA8YnJhbmROYW1lPuWVhuWTgeWTgeeJjOWQjeensDwvYnJhbmROYW1lPgogICAgICA8dmVuZG9y\n" +
                "TmFtZT7kvpvlupTllYblkI3np7A8L3ZlbmRvck5hbWU+CiAgICAgIDx2ZW5kb3JDb2RlPuS+m+W6\n" +
                "lOWVhuWvueW6lOWVhuWTgeS7o+eggTwvdmVuZG9yQ29kZT4KICAgICAgPGNvbnNpZ25tZW50PuaY\n" +
                "r+WQpuWvhOWUrigwOuWQpu+8mzE65pivKTwvY29uc2lnbm1lbnQ+CiAgICAgIDxkaXNjb250aW51\n" +
                "ZWQ+5piv5ZCm5pat6LSnKDA65ZCm77ybMTrmmK8pPC9kaXNjb250aW51ZWQ+CiAgICAgIDxjb2xv\n" +
                "cj7popzoibI8L2NvbG9yPgogICAgICA8c3BlY2lmaWNhdGlvbj7llYblk4HmnaHnoIHop4TmoLw8\n" +
                "L3NwZWNpZmljYXRpb24+CiAgICAgIDx1bml0UHJpY2U+5ZWG5ZOB5Y2V5Lu3PC91bml0UHJpY2U+\n" +
                "CiAgICAgIDxzdGFuZGFyZENvc3Q+5qCH5YeG5oiQ5pysPC9zdGFuZGFyZENvc3Q+CiAgICAgIDxh\n" +
                "dmVyYWdlQ29zdD7lubPlnYfmiJDmnKw8L2F2ZXJhZ2VDb3N0PgogICAgICA8ZGlzY291bnQ+5oqY\n" +
                "5omjPC9kaXNjb3VudD4KICAgICAgPGxwUHJpY2U+5pyA5ZCO6YeH6LSt5Lu3PC9scFByaWNlPgog\n" +
                "ICAgICA8cGFyZW50Q29kZT7niLbnuqdJVEVN57yW56CBPC9wYXJlbnRDb2RlPgogICAgICA8dW5p\n" +
                "dD7orqHph4/ljZXkvY08L3VuaXQ+CiAgICAgIDxkZXNjcmlwdGlvbj5JVEVN5o+P6L+wPC9kZXNj\n" +
                "cmlwdGlvbj4KICAgICAgPGN1cnJlbmN5PuWkluaxhyDlpoLvvJpHQlA8L2N1cnJlbmN5PgogICAg\n" +
                "ICA8Zm9yZWlnbkNvc3Q+5aSW5rGH6YeR6aKdPC9mb3JlaWduQ29zdD4KICAgICAgPGFjdGl2ZT7l\n" +
                "lYblk4HnirbmgIHvvIgwOuaXoOaViO+8mzE65pyJ5pWI77yJPC9hY3RpdmU+CiAgICA8L2l0ZW0+\n" +
                "CiAgPC9pdGVtcz4KPC9yZXF1ZXN0Pgo=";
    }
}

