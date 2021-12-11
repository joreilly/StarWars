import Foundation
import SwiftUI
import shared

class StarWarsViewModel: ObservableObject {
    let repository = StarWarsRepository()
    @Published public var peopleList: [Person] = []
    @Published public var filmList: [Film] = []
    
    func fetchPeople() {
        repository.getPeople { peopleList in
            self.peopleList = peopleList
        }
    }
    
    func fetchFilms() {
        repository.getFilms { filmList in
            self.filmList = filmList
        }
    }

}

