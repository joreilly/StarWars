import Foundation
import SwiftUI
import shared


extension Person: Identifiable { }
extension Film: Identifiable { }


@MainActor
class StarWarsViewModel: ObservableObject {
    let repository = StarWarsRepository()
    @Published public var peopleList: [Person] = []
    @Published public var filmList: [Film] = []


    func startObservingPeople() async {
        do {
            let stream = repository.people.asAsyncSequence()
            for try await data in stream {
                self.peopleList = data
            }
        } catch {
            print("Failed with error: \(error)")
        }
    }
    
    func startObservingFilms() async {
        do {
            let stream = repository.films.asAsyncSequence()
            for try await data in stream {
                self.filmList = data
            }
        } catch {
            print("Failed with error: \(error)")
        }
    }
    
}

