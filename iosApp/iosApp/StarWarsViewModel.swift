import Foundation
import SwiftUI
import shared
import KMPNativeCoroutinesAsync


@MainActor
class StarWarsViewModel: ObservableObject {
    let repository = StarWarsRepository()
    @Published public var peopleList: [PersonFragment] = []
    @Published public var filmList: [FilmFragment] = []
    
    
    func startObservingPeople() async {
        do {
            let stream = asyncStream(for: repository.peopleNative)
            for try await data in stream {
                self.peopleList = data
            }
        } catch {
            print("Failed with error: \(error)")
        }
    }
    
    func startObservingFilms() async {
        do {
            let stream = asyncStream(for: repository.filmsNative)
            for try await data in stream {
                self.filmList = data
            }
        } catch {
            print("Failed with error: \(error)")
        }
    }
    
}

