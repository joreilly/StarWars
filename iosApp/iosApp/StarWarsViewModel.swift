import Foundation
import SwiftUI
import shared
import KMPNativeCoroutinesAsync


extension PersonFragment: Identifiable { }
extension FilmFragment: Identifiable { }


@MainActor
class StarWarsViewModel: ObservableObject {
    let repository = StarWarsRepository()
    @Published public var peopleList: [PersonFragment] = []
    @Published public var filmList: [FilmFragment] = []
    
    
    func startObservingPeople() async {
        do {
            let stream = asyncSequence(for: repository.people)
            for try await data in stream {
                self.peopleList = data
            }
        } catch {
            print("Failed with error: \(error)")
        }
    }
    
    func startObservingFilms() async {
        do {
            let stream = asyncSequence(for: repository.films)
            for try await data in stream {
                self.filmList = data
            }
        } catch {
            print("Failed with error: \(error)")
        }
    }
    
}

