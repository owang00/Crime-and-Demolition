import scrapy
from urllib.parse import urlparse


class QuotesSpider(scrapy.Spider):
    name = "nypd"

    def start_requests(self):
        urls = [
            'https://www1.nyc.gov/site/nypd/bureaus/patrol/precincts-landing.page',
        ]
        for url in urls:
            yield scrapy.Request(url=url, callback=self.parse)


    def parse_precinct_page(self, response):
        precinct = response.xpath('//div[@class="span6 about-description"]/h1/text()').get()

        zipcode = response.xpath(
            '//div[@class="span6 about-description"]//h2[contains(text(), "Contact")]/following-sibling::p/text()').getall()
            
        if not zipcode:
            zipcode = response.xpath(
            '//div[@class="span6 about-description"]//h3[contains(text(), "Contact")]/following-sibling::p/text()').getall()

        if len(zipcode) < 2:
            from scrapy.shell import inspect_response
            inspect_response(response, self)

        zipcode = zipcode[1].split(',')[-1].strip()
        

        yield {

            "precinct": precinct,
            "zipcode": zipcode
        }

    def parse(self, response):
        domain = '{uri.scheme}://{uri.netloc}/'.format(uri=urlparse(response.url))
        links = response.xpath('//td[@data-label="Precinct"]/a/@href')

        for link in links:
            yield scrapy.Request(f"{domain}{link.get()}", callback=self.parse_precinct_page)

