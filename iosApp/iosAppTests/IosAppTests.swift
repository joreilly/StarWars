import XCTest
import shared

@MainActor
class StarWarsIosAppTests: XCTestCase {

    func testGetFilmList() async throws {
        let repository = StarWarsRepository()

        for try await filmList in repository.films.asAsyncSequence() {
            XCTAssertNotNil(filmList)
            print(filmList)
        }
    }
}
