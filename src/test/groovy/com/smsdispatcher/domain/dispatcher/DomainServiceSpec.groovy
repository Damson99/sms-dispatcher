package com.smsdispatcher.domain.dispatcher

import com.smsdispatcher.domain.subscriber.NetworkMembershipEnum
import com.smsdispatcher.domain.subscriber.NetworkSubscriber
import spock.lang.Specification
import spock.lang.Subject

@Subject(DomainService)
class DomainServiceSpec extends Specification {
    ContentEvaluatorProvider contentEvaluatorProvider = Mock(ContentEvaluatorProvider)
    UrlDetector urlDetector = new UrlDetector()

    def "should detect all #expectedUrls in #message"() {
        given:
            DomainService domainService = new DispatchDomainService(contentEvaluatorProvider, urlDetector)
        when:
            List<String> urls = domainService.getUrlsFromMessage(message)
        then:
            urls == expectedUrls
        where:
            expectedUrls                                                                                                                                                                            | message
            List.of("https://www.trasdstination.com", "http://www.1111trasdstination.com")                                                                                                          | "Witaj! Chciałem Ci polecić to niesamowite miejsce na Ziemi: https://www.trasdstination.com oraz to miejsce http://www.1111trasdstination.com"
            List.of("https://www.latestsmartphone.com")                                                                                                                                             | "Czy słyszałeś o najnowszym modelu smartfona? Sprawdź go tutaj: https://www.latestsmartphone.com To naprawdę rewolucyjne urządzenie!"
            List.of("https://www.example2.com", "https://www.latestsmartphone.com", "http://www.1111trasdstination.com", "http://www.2222trasdstination.com", "http://www.3333trasdstination.com",) | "https://www.example2.com https://www.latestsmartphone.com \nhttp://www.1111trasdstination.com\nhttp://www.2222trasdstination.com\nhttp://www.3333trasdstination.com"
    }

    def "should return #expectedEvaluation based on #subscriber and #message - urls occurrences"() {
        given:
            DomainService domainService = new DispatchDomainService(contentEvaluatorProvider, urlDetector)
        when:
            EvaluatedContent evaluation = domainService.evaluateContentFor(message, subscriber)
        then:
            evaluation == expectedEvaluation
        where:
            expectedEvaluation          | subscriber                                                          | message
            EvaluatedContent.OK         | new NetworkSubscriber(null, null, NetworkMembershipEnum.MEMBER)     | "Czy słyszałeś o najnowszym modelu smartfona? Sprawdź go tutaj: https://www.latestsmartphone.com To naprawdę rewolucyjne urządzenie!"
            EvaluatedContent.NONE | new NetworkSubscriber(null, null, NetworkMembershipEnum.NOT_MEMBER) | "Czy słyszałeś o najnowszym modelu smartfona? Sprawdź go tutaj: https://www.latestsmartphone.com To naprawdę rewolucyjne urządzenie!"
            EvaluatedContent.THREAT     | new NetworkSubscriber(null, null, NetworkMembershipEnum.MEMBER)     | "https://www.example2.com https://www.latestsmartphone.com \nhttp://www.1111trasdstination.com\nhttp://www.2222trasdstination.com\nhttp://www.3333trasdstination.com"
            EvaluatedContent.NONE | new NetworkSubscriber(null, null, NetworkMembershipEnum.NOT_MEMBER) | "https://www.example2.com https://www.latestsmartphone.com \nhttp://www.1111trasdstination.com\nhttp://www.2222trasdstination.com\nhttp://www.3333trasdstination.com"
    }

    def "should return THREAT when returned from #provider"() {
        given:
            NetworkSubscriber subscriber = NetworkSubscriber.from(null, null)
            String message = "Czy słyszałeś o najnowszym modelu smartfona? Sprawdź go tutaj: https://www.latestsmartphone.com"
            DomainService domainService = new DispatchDomainService(provider, urlDetector)
        when:
            EvaluatedContent evaluation = domainService.evaluateContentFor(message, subscriber)
        then:
            evaluation == evaluatedContent
        where:
            evaluatedContent        | provider
            EvaluatedContent.THREAT | new ContentEvaluatorThreatMock()
            EvaluatedContent.OK     | new ContentEvaluatorOKMock()
    }

    class ContentEvaluatorThreatMock implements ContentEvaluatorProvider {

        @Override
        EvaluationOfContent performEvaluating(String url) {
            return EvaluationOfContent.THREAT
        }
    }

    class ContentEvaluatorOKMock implements ContentEvaluatorProvider {

        @Override
        EvaluationOfContent performEvaluating(String url) {
            return EvaluationOfContent.OK
        }
    }
}
