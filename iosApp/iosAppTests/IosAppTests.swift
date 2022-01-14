import XCTest
import KMPNativeCoroutinesAsync
import shared

@MainActor
class StarWarsIosAppTests: XCTestCase {

    func testGetFilmList() async throws {
        let repository = StarWarsRepository()
        
        let stream = asyncStream(for: repository.filmsNative)
        for try await filmList in stream {
            XCTAssertNotNil(filmList)
            print(filmList)
        }
    }
}
