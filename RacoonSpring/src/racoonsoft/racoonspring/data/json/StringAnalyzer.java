package racoonsoft.racoonspring.data.json;

import racoonsoft.library.helper.TypeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class StringAnalyzer
{
    public static int JSON_TYPE_NUMBER = 1;
    public static int JSON_TYPE_STRING = 2;
    public static int JSON_TYPE_BOOLEAN = 3;
    public static int JSON_TYPE_NULL = 4;
    public static int JSON_TYPE_OBJECT = 5;
    public static int JSON_TYPE_ARRAY = 6;
    public static int JSON_TYPE_UNKNOWN = 7;

	public static boolean avalChar(String body,int index)
	{
		if(index<=0)
		{
			return true;
		}
		if(body.length()>index)
		{
			return body.charAt(index-1)!='\\';
		}
		return true;
	}
    public static ArrayList<String> getJSONPairs(String body)
	{
		int type = StringAnalyzer.getValueType(body);
		if(type == StringAnalyzer.JSON_TYPE_ARRAY||type == StringAnalyzer.JSON_TYPE_OBJECT)
		{
			body = body.substring(1, body.length()-1);
		}

		ArrayList<String> pairs = new ArrayList();
		boolean in_quotes = false;
		int in_figure = 0;
		int in_brakets = 0;
		int current_start_index = 0;
		for(int i=0; i<body.length(); i++)
		{
			if(body.charAt(i)=='"'&&avalChar(body,i))
			{
				in_quotes = !in_quotes;
			}
			if(body.charAt(i)=='{'&&!in_quotes&&avalChar(body,i))
			{
				in_figure ++;
			}
			if(body.charAt(i)=='}'&&!in_quotes&&avalChar(body,i))
			{
				in_figure --;
			}
			if(body.charAt(i)=='['&&!in_quotes&&avalChar(body,i))
			{
				in_brakets ++;
			}
			if(body.charAt(i)==']'&&!in_quotes&&avalChar(body,i))
			{
				in_brakets --;
			}

			if(!in_quotes&&in_figure==0&&in_brakets==0&&body.charAt(i)==',') // Pair ends...
			{
				if(pairs.isEmpty())
				{
					current_start_index--;
				}

				pairs.add(body.substring(current_start_index+1, i));
				current_start_index = i;
			}
		}
		if(pairs.isEmpty())
		{
			current_start_index--;
		}
		if(body.length()>0)
		{
			pairs.add(body.substring(current_start_index+1, body.length()));
		}
		return pairs;
    }
    public static ArrayList<String> getJSONArrayElements(String body)
    {
		int type = StringAnalyzer.getValueType(body);

		ArrayList<String> elems = new ArrayList();
		boolean in_quotes = false;
		int in_figure = 0;
		int in_brakets = 0;
		int current_start_index = 0;
		for(int i=0; i<body.length(); i++)
		{
			if(body.charAt(i)=='"'&&avalChar(body,i))
			{
				in_quotes = !in_quotes;
			}
			if(body.charAt(i)=='{'&&!in_quotes&&avalChar(body,i))
			{
				in_figure ++;
			}
			if(body.charAt(i)=='}'&&!in_quotes&&avalChar(body,i))
			{
				in_figure --;
			}
			if(body.charAt(i)=='['&&!in_quotes&&avalChar(body,i))
			{
				in_brakets ++;
			}
			if(body.charAt(i)==']'&&!in_quotes&&avalChar(body,i))
			{
				in_brakets --;
			}
			
			if(!in_quotes&&in_figure==0&&in_brakets==0&&body.charAt(i)==',') // Pair ends...
			{
				if(elems.isEmpty())
				{
					current_start_index--;
				}
				String curr_elem = body.substring(current_start_index+1, i);
				elems.add(curr_elem);
				current_start_index = i;
			}
		}
		if(elems.isEmpty())
		{
			current_start_index--;
		}
		if(body.length()>0)
		{
			elems.add(body.substring(current_start_index+1, body.length()));
		}
		return elems;
	}
	public static HashMap<String,String> extractJSONPairs(String body)
	{
		HashMap<String,String> res = new HashMap<String, String>();
		ArrayList<String> pairs = getJSONPairs(body);
		for(int i=0; i<pairs.size(); i++)
		{
			String[] pair = pairs.get(i).split("\\:", 2);
			res.put(pair[0].trim(), pair[1].trim());
		}
		return res;
    }
    public static ArrayList<String> extractJSONArray(String value)
    {
		ArrayList<String> result = new ArrayList<String>();
		value = value.trim();
		value = value.substring(1, value.length()-1);

		ArrayList<String> array_elements = getJSONArrayElements(value);
		return array_elements;
    }
    public static HashMap<String,Object> extractJSONObject(String value)
    {
		return new HashMap<String,Object>();
    }
    public static int getValueType(String value)
    {
		if(value.length()<=0)
		{
			return JSON_TYPE_UNKNOWN;
		}
		if(value.charAt(0)=='"'&&value.charAt(value.length()-1)=='"')
		{
			return JSON_TYPE_STRING;
		}
		if(TypeHelper.isDouble(value))
		{
			return JSON_TYPE_NUMBER;
		}
		if(TypeHelper.isBoolean(value))
		{
			return JSON_TYPE_BOOLEAN;
		}
		if(TypeHelper.isNull(value))
		{
			return JSON_TYPE_NULL;
		}
		if(value.charAt(0)=='{'&&value.charAt(value.length()-1)=='}')
		{
			return JSON_TYPE_OBJECT;
		}
		if(value.charAt(0)=='['&&value.charAt(value.length()-1)==']')
		{
			return JSON_TYPE_ARRAY;
		}
        System.out.println(value+" UNKNOWN");
		return JSON_TYPE_UNKNOWN;
    }
    public static HashMap<String,Object> extractPairs(HashMap<String,String> pairs)
    {
		HashMap<String,Object> result = new HashMap<String, Object>();
		Iterator<String> namesIter = pairs.keySet().iterator();
		while(namesIter.hasNext())
		{
			String name = namesIter.next().trim();
			String value = pairs.get(name).trim();
			int type = StringAnalyzer.getValueType(value);
			if(type == StringAnalyzer.JSON_TYPE_STRING)
			{
				result.put(name.replace("\"", ""), value.replace("\"", ""));
			}
			if(type == StringAnalyzer.JSON_TYPE_NUMBER)
			{
				if(TypeHelper.isInt(value))
				{
					result.put(name.replace("\"", ""), Integer.parseInt(value));
				}
				else
				{
					result.put(name.replace("\"", ""), Double.parseDouble(value));
				}
			}
			if(type == StringAnalyzer.JSON_TYPE_BOOLEAN)
			{
				result.put(name.replace("\"", ""), Boolean.parseBoolean(value));
			}
			if(type == StringAnalyzer.JSON_TYPE_NULL)
			{
				result.put(name.replace("\"", ""), null);
			}
			if(type == StringAnalyzer.JSON_TYPE_ARRAY)
			{
				ArrayList<String> arr = extractJSONArray(value);
				result.put(name.replace("\"", ""), StringAnalyzer.extractArray(arr));
			}
			if(type == StringAnalyzer.JSON_TYPE_OBJECT)
			{
				HashMap<String,String> pp = StringAnalyzer.extractJSONPairs(value.substring(0, value.length()));
				HashMap<String,Object> re = extractPairs(pp);
				result.put(name.replace("\"", ""), re);
			}
		}
		return result;
    }
    public static ArrayList<Object> extractArray(ArrayList<String> pairs)
    {
		ArrayList<Object> result = new ArrayList<Object>();
		for(int i=0; i<pairs.size(); i++)
		{
			String value = pairs.get(i).trim();
			int type = StringAnalyzer.getValueType(value);
			if(type == StringAnalyzer.JSON_TYPE_STRING)
			{
				result.add(value.replace("\"", ""));
			}
			if(type == StringAnalyzer.JSON_TYPE_NUMBER)
			{
				result.add(Double.parseDouble(value));
			}
			if(type == StringAnalyzer.JSON_TYPE_BOOLEAN)
			{
				result.add(Boolean.parseBoolean(value));
			}
			if(type == StringAnalyzer.JSON_TYPE_NULL)
			{
				result.add(null);
			}
			if(type == StringAnalyzer.JSON_TYPE_ARRAY)
			{
				ArrayList<String> arr = extractJSONArray(value);
				result.add(StringAnalyzer.extractArray(arr));
			}
			if(type == StringAnalyzer.JSON_TYPE_OBJECT)
			{
				HashMap<String,String> pp = StringAnalyzer.extractJSONPairs(value.substring(0, value.length()));
				HashMap<String,Object> re = extractPairs(pp);
				result.add(re);
			}
		}
		return result;
    }
}
