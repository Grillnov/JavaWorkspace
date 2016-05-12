import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.*;
import java.io.FileWriter;
import java.io.IOException;

public class Crawler
{
	private static Set<String> isVisited = new HashSet<String>();
	private static Queue<String> unVisited = new LinkedList<String>();
	private static HttpKit kit = new HttpKit();
	private static Pattern urlPattern = 
			Pattern.compile("<a\\s*href=[\'\"](.+?)[\'\"]", Pattern.CASE_INSENSITIVE);
	
	public void crawl(String initialURL) throws IOException
	{
		init(initialURL);
		while(!unVisited.isEmpty())
		{
			String currentURL = unVisited.poll();
			String urlContent = kit.getPageContent(currentURL);
			if(urlContent != null)
			{
				save2File(currentURL, urlContent);
				parseURL(currentURL, urlContent);
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Crawler spider = new Crawler();
		if(args.length > 1)
			spider.crawl(args[1]);
		else
			spider.crawl("http://www.pku.edu.cn/");
	}
	
	private void init(String initialURL)
	{
		isVisited.add(initialURL);
		unVisited.offer(initialURL);
	}
	
	private void save2File(String url, String pageContent) throws IOException
	{
		String fileName = "./" + url.substring(url.indexOf("://") + 3, url.length()).replaceAll("[/\\?=]", "-");
		FileWriter page = new FileWriter(fileName);
		try
		{
			page.write(pageContent);
			page.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void parseURL(String srcURL, String pageContent)
	{
		Matcher urlMatcher = urlPattern.matcher(pageContent);
		while(urlMatcher.find())
		{
			String urlFound = urlMatcher.group(1);
			if(urlFound.indexOf("://") == -1)
			{
				int i = srcURL.length() - 1;
				for(; i > 0; --i)
				{
					if(srcURL.charAt(i) != '/')
						break;
				}
				int j = 0;
				for(; j != urlFound.length(); ++j)
				{
					if(urlFound.charAt(j) != '/')
						break;
				}
				urlFound = srcURL.substring(0, i + 1) + "/" + urlFound.substring(j, urlFound.length());
			}
			if(!isVisited.contains(urlFound))
			{
				isVisited.add(urlFound);
				unVisited.offer(urlFound);
			}
		}
	}
}
